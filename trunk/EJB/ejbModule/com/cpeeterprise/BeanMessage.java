package com.cpeeterprise;

import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Message;

/**
 * Session Bean implementation class BeanMessage
 */
@Stateful(mappedName = "Message")
@TransactionManagement(TransactionManagementType.BEAN)
public class BeanMessage implements BeanMessageRemote {
	
	 @PersistenceContext
	 private EntityManager em;
	 
    /**
     * Default constructor. 
     */
    public BeanMessage() {
        // TODO Auto-generated constructor stub
    }
    
    public void persist (Message message) {
        em.persist (message);
      }

      public void delete (Message message) {
        Message t = em.merge (message);
        em.remove( t );

      }

      public void update (Message message) {
        em.merge (message);
      }

      public List <Message> findMessages () {

        return (List <Message>) em.createQuery("select t from Message t").getResultList();
      }

      public Message findMessage (String id) {

        return (Message) em.find(Message.class, Long.parseLong(id));
      }


}
