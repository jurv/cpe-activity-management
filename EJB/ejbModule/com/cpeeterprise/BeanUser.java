package com.cpeeterprise;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import model.Message;
import model.Customer;
import model.User;

/**
 * Session Bean implementation class BeanUser
 */
@Stateful(mappedName = "User")
@TransactionManagement(TransactionManagementType.CONTAINER)
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
		em.flush();
		em.refresh(user);
	}

	public void delete(User user) {
		User t = em.merge(user);
		em.remove(t);

	}

	public void update(User user) {
		em.merge(user);
		em.flush();
	}

	public List<User> findUsers() {

		return (List<User>) em.createQuery("select t from User t")
				.getResultList();
	}
	
	public List<User> findUsersByProject(int prjId) {

		return (List<User>) em.createQuery("select t from User t where usr_id IN (select up from User2Project up where prj_id = '" + prjId + "')")
				.getResultList();
	}

	public User findUser(int id) {

		return (User) em.find(User.class, id);
	}
	
	public List<User> findActiveUsers() {
		return (List <User>) em.createQuery("select c from User c where usr_isdeleted = 'O'").getResultList();
	}

	@Override
	public int connectUser(String login, String password) {

		List<User> usrs = new ArrayList(em.createQuery("select t from User t where usr_login = '" + login + "' and usr_password = '" + password + "'")
				.getResultList());
		if (!usrs.isEmpty()) {
			// On connecte l'utilisateur
			usrs.get(0).setUstId(2);
			update(usrs.get(0));
			return usrs.get(0).getUsrId();
		}
		return -1;
	}

	@Override
	public boolean disconnectUser(User user) {

		// On déconnecte l'utilisateur
		user.setUstId(1);
		update(user);

		return true;
	}

	@Override
	public List<User> getConnectedUsers() {

		return (List<User>) em.createQuery("select t from User t where ust_id <> '1'")
				.getResultList();
	}

	@Override
	public List<User> findCdps() {
		return (List<User>) em.createQuery("select t from User t where fct_id = '1'").getResultList();
	}

	public List<User> findUserWithChatConv(int usrId, int prjId) {
		ArrayList<Message> msg = new ArrayList<Message>();
		String usrIds = "";
		String separator = "";
		msg.addAll(em.createQuery("select m from Message m where usr_sender_id = " + usrId + " and prj_id = " + prjId + " ").getResultList());
		msg.addAll(em.createQuery("select m from Message m where usr_receiver_id = " + usrId + " and prj_id = " + prjId + " ").getResultList());
		for(Message m : msg) {
			usrIds += separator + m.getSender().getUsrId();
			separator = ",";
		}
		return (List<User>) em.createQuery("select t from User t where usr_id IN (" + usrIds + ") and usr_id <> " + usrId)
				.getResultList();
	}
	
	public List<User> findUserWithChatConv(int usrId) {
		ArrayList<Message> msg = new ArrayList<Message>();
		String usrIds = "";
		String separator = "";
		msg.addAll(em.createQuery("select m from Message m where usr_sender_id = " + usrId + " ").getResultList());
		msg.addAll(em.createQuery("select m from Message m where usr_receiver_id = " + usrId + " ").getResultList());
		for(Message m : msg) {
			usrIds += separator + m.getSender().getUsrId();;
			separator = ",";
		}
		return (List<User>) em.createQuery("select t from User t where usr_id IN (" + usrIds + ") and usr_id <> " + usrId)
				.getResultList();
	}
	
	public void logicalDelete(User usr) {
		User user = em.merge (usr);
		user.setUsrIsdeleted((byte)1);
		update(user);
	  }
}
