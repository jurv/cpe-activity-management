package managedbeans;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;

import model.User;
import model.Message;
import model.Project;


import com.cpeeterprise.BeanMessageRemote;
import com.cpeeterprise.BeanProjectRemote;
import com.cpeeterprise.BeanUserRemote;

@ManagedBean
@ViewScoped
public class CreateMessageBean {
	
	@EJB
	public BeanUserRemote userRemote;
	@EJB
	public BeanMessageRemote messageRemote;
	@EJB
	public BeanProjectRemote projectRemote;
	private User currentUser;
	private Message newMessage = new Message();
	private ArrayList<Project> listProjects = new ArrayList<Project>();
	private ArrayList<User> listReceivers = new ArrayList<User>();
	
	public void initPage() {
		
		// On remplit la liste des projets de l'utilisateur
		this.listProjects.clear();
		this.listProjects.addAll(projectRemote.findProjectsByUser(currentUser.getUsrId()));
		
		// On remplit la liste d'utilisateurs
		if(this.listProjects.size() > 0) {
			int prjId = this.listProjects.get(0).getPrjId();
			this.listReceivers.clear();
			this.listReceivers.addAll(userRemote.findUsersByProject(prjId));
		}
		
		// On initialise le message
		this.newMessage.setMsgDate(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
		this.newMessage.setSender(currentUser);
		this.newMessage.setMsgIsread((byte)0);
		this.newMessage.setMsgIsdeleted((byte)0);
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public Message getNewMessage() {
		return newMessage;
	}

	public void setNewMessage(Message newMessage) {
		this.newMessage = newMessage;
	}

	public ArrayList<Project> getListProjects() {
		return listProjects;
	}

	public void setListProjects(ArrayList<Project> listProjects) {
		this.listProjects = listProjects;
	}

	public ArrayList<User> getListReceivers() {
		return listReceivers;
	}

	public void setListReceivers(ArrayList<User> listReceivers) {
		this.listReceivers = listReceivers;
	}
	
	public void selectedProjectChanged(ValueChangeEvent e) {
		int prjId = Integer.parseInt(e.getNewValue().toString());
		if(prjId > 0) {
			this.listReceivers.clear();
			this.listReceivers.addAll(userRemote.findUsersByProject(prjId));
		}
	}
	
	public String sendMessage() {
		messageRemote.persist(this.newMessage);
		this.newMessage = new Message();
		this.newMessage.setMsgDate(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
		this.newMessage.setSender(currentUser);
		this.newMessage.setMsgIsread((byte)0);
		this.newMessage.setMsgIsdeleted((byte)0);
		
		return "Messages";
	}
}