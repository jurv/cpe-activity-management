package com.cpeeterprise;

import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import model.User;

/**
 * Session Bean implementation class BeanUser
 */
@Stateful(mappedName = "User")
@TransactionManagement(TransactionManagementType.BEAN)
public class BeanUser implements BeanUserRemote {

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public BeanUser() {
		// TODO Auto-generated constructor stub
	}

	public void persist(User user) {
		em.persist(user);
	}

	public void delete(User user) {
		User t = em.merge(user);
		em.remove(t);

	}

	public void update(User user) {
		em.merge(user);
	}

	public List<User> findUsers() {

		return (List<User>) em.createQuery("select t from User t")
				.getResultList();
	}

	public User findUser(int id) {

		return (User) em.find(User.class, id);
	}

	@Override
	public int connectUser(String login, String password) {

		List<User> usrs = (List<User>) em.createQuery(
				"select t from User t where usr_login = '" + login
						+ "' and usr_password = '" + password + "'")
				.getResultList();
		if (!usrs.isEmpty()) {
			// On connecte l'utilisateur
			//usrs.get(0).setUstId(2);
			//update(usrs.get(0));
			return usrs.get(0).getUsrId();
		}
		return -1;
	}

	@Override
	public boolean disconnectUser(User user) {

		// On déconnecte l'utilisateur
		//user.setUstId(1);
		//update(user);

		return true;
	}

	@Override
	public List<User> getConnectedUsers() {

		return (List<User>) em.createQuery(
				"select t from User t where ust_id <> 1").getResultList();
	}

}
