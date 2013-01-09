package com.cpeeterprise;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
@TransactionManagement(TransactionManagementType.CONTAINER)
public class BeanMessage implements BeanMessageRemote {
	
	 @PersistenceContext
	 private EntityManager em;
	 
    /**
     * Default constructor. 
     */
    public BeanMessage() {
        // TODO Auto-generated constructor stub
    }
    
    public Message persist (Message message) {
        em.persist (message);
        em.flush();
        em.refresh(message);
        return message;
      }

      public void delete (Message message) {
        Message t = em.merge (message);
        em.remove( t );

      }

      public void update (Message message) {
        em.merge (message);
        em.flush();
      }

      public List <Message> findMessages () {

        return (List <Message>) em.createQuery("select t from Message t").getResultList();
      }

      public Message findMessage (String id) {

        return (Message) em.find(Message.class, Long.parseLong(id));
      }
      
      public List <Message> findOlderMessages (int senderId, int receiverId, int messageId) {

    	  SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	  Calendar now = GregorianCalendar.getInstance();
    	  now.add(Calendar.MINUTE, -10);
    	  return (List <Message>) em.createQuery("select t from Message t where msg_date >= '" + date_format.format(now.getTime()) + "' and usr_sender_id = " + senderId + " and usr_receiver_id = " + receiverId).getResultList();
      }


}
