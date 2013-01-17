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
      
      public void logicalDelete (Message message) {
          Message t = em.merge (message);
          t.setMsgIsdeleted((byte)1);
          update(t);

        }

      public void update (Message message) {
        em.merge (message);
        em.flush();
      }

      public List <Message> findMessages () {

        return (List <Message>) em.createQuery("select t from Message t").getResultList();
      }

      public Message findMessage (int id) {

        return (Message) em.find(Message.class, id);
      }
      
      public List <Message> findOlderMessages (int senderId, int receiverId) {

    	  SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	  Calendar now = GregorianCalendar.getInstance();
    	  now.add(Calendar.MINUTE, -10);
    	  return (List <Message>) em.createQuery("select t from Message t where msg_date >= '" + date_format.format(now.getTime()) + "' and usr_sender_id = " + senderId + " and usr_receiver_id = " + receiverId).getResultList();
      }

      public List<Message> findDeletedMessagesFor(int receiverId) {
    	  return (List <Message>) em.createQuery("select t from Message t where (usr_receiver_id = " + receiverId + " or usr_sender_id = " + receiverId + ") and msg_isdeleted = '1' and msg_subject <> 'Chat'").getResultList();
      }
      
      public List<Message> findReceivedMessagesFor(int receiverId) {
    	  return (List <Message>) em.createQuery("select t from Message t where usr_receiver_id = " + receiverId + " and msg_isdeleted = '0' and msg_subject <> 'Chat'").getResultList();
      }
      
      public List<Message> findSentMessagesFor(int senderId) {
    	  return (List <Message>) em.createQuery("select t from Message t where usr_sender_id = " + senderId + " and msg_subject <> 'Chat' ").getResultList();
      }
      
      public List<Message> findMessagesForConv(int usrId1, int usrId2) {
    	  return (List <Message>) em.createQuery("select t from Message t where (usr_sender_id = " + usrId1 + " and  usr_receiver_id = " + usrId2 + ") or (usr_sender_id = " + usrId2 + " and  usr_receiver_id = " + usrId1 + ") order by msg_date ASC").getResultList();
      }
}
