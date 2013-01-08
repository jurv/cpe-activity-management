package managedbeans;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;


import model.Message;
import model.User;


import com.cpeeterprise.BeanMessageRemote;
import com.cpeeterprise.BeanUserRemote;

@ManagedBean
@SessionScoped
public class ConnectedUsersBean {
	
	@EJB
	public BeanUserRemote userRemote;
	@EJB
	public BeanMessageRemote messageRemote;
	private User currentUser;
	private List<User> connectedUsers;
	private String connectedUsersString = "";
	private List<Message> listMessages = new ArrayList();
	private Message last;
	private String listMessagesString = "";
	private String newMessage = "";

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public List<User> getConnectedUsers() {
		return connectedUsers;
	}

	public void setConnectedUsers(List<User> connectedUsers) {
		this.connectedUsers = connectedUsers;
	}

	public String getConnectedUsersString() {
		
		setConnectedUsers(userRemote.getConnectedUsers());
		String connectedUsersString = "";
		for( User cur : connectedUsers ) {
			if(cur.getUsrId() != currentUser.getUsrId())
				connectedUsersString += "<div class='chat-element' pid='" + cur.getUsrId() + "' pfname='" + cur.getUsrFirstname() + "' plname='" + cur.getUsrLastname() + "' >" + cur.getUsrFirstname() + " " + cur.getUsrLastname() + "</div>" + "<br/>";
		}
		
		return connectedUsersString;
	}

	public void setConnectedUsersString(String connectedUsersString) {
		this.connectedUsersString = connectedUsersString;
	}
	
	public void sendMessage (ActionEvent evt) {
		
		// Get the receiver Id
		int receiverId = Integer.parseInt((String)FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("receiverid"));
		Message mess = new Message();
		mess.setMsgContent(newMessage);
		mess.setMsgSubject("Chat");
		mess.setUsrSenderId(this.currentUser.getUsrId());
		mess.setUsrReceiverId(receiverId);
		mess = messageRemote.persist(mess);
		this.listMessages.add(mess);
		last = mess;
		newMessage = "";
	}

	public List<Message> getListMessages() {
		return listMessages;
	}

	public void setListMessages(List<Message> listMessages) {
		this.listMessages = listMessages;
	}

	public String getListMessagesString() {
		
		listMessagesString = "";
		newMessage = "";
		
		// On récupère les messages pour cet utilisateur
		// Les messages qui ont un identifiant > au dernier qu'il a écrit
		if(last != null) {
			for(Message mess : messageRemote.findOlderMessages(last.getUsrReceiverId(), last.getUsrSenderId(), last.getMsgId()))
				if(!this.listMessages.contains(mess))
					this.listMessages.add(mess);
		}
		Collections.sort(this.listMessages);
		for(Message mess : this.listMessages) {
			
			User sender = userRemote.findUser(mess.getUsrSenderId());
			this.listMessagesString += "<strong>" + sender.getUsrFirstname() + "</strong>" + " : " + mess.getMsgContent() + "<br/>";
				
			if(mess.getMsgIsread() == 0) {
				if(mess.getUsrSenderId() != currentUser.getUsrId()) {
					mess.setMsgIsread((byte)1);
					messageRemote.update(mess);
				}
			}
		}
		return listMessagesString;
	}
	
	public void refreshMessages() {
		getListMessagesString();
	}

	public void setListMessagesString(String listMessagesString) {
		this.listMessagesString = listMessagesString;
	}

	public String getNewMessage() {
		return newMessage;
	}

	
	public void setNewMessage(String newMessage) {
		this.newMessage = newMessage;
	}
}