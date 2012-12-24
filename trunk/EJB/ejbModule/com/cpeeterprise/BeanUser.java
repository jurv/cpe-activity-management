package com.cpeeterprise;

import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.User;

/**
 * Session Bean implementation class BeanUser
 */
@Stateful(mappedName = "User")
@TransactionManagement(TransactionManagementType.BEAN)
public class BeanUser implements BeanUserRemote {
	
	 @PersistenceContext
	 private EntityManager em;
	 
    /**
     * Default constructor. 
     */
    public BeanUser() {
        // TODO Auto-generated constructor stub
    }
    
    public void persist (User user) {
        em.persist (user);
      }

      public void delete (User user) {
        User t = em.merge (user);
        em.remove( t );

      }

      public void update (User user) {
        em.merge (user);
      }

      public List <User> findUsers () {

        return (List <User>) em.createQuery("select t from User t").getResultList();
      }

      public User findUser (String id) {

        return (User) em.find(User.class, Long.parseLong(id));
      }

	@Override
	public String connectUser(String login, String password) {
		
		List<User> usrs = (List <User>) em.createQuery("select t from User t where usr_login = '" + login + "' and usr_password = '" + password + "'").getResultList();
		if(!usrs.isEmpty())
			return "Connected";
		return "Unregistered";
	}


}
