package managedbeans;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIInput;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;


import model.Message;
import model.User;


import com.cpeeterprise.BeanMessageRemote;
import com.cpeeterprise.BeanUserRemote;

@ManagedBean
@ViewScoped
public class ConnectedUsersBean {
	
	@EJB
	public BeanUserRemote userRemote;
	@EJB
	public BeanMessageRemote messageRemote;
	private User currentUser;
	private List<User> connectedUsers;
	private String connectedUsersString = "";
	private List<Message> listMessages = new ArrayList();
	private Message lastMessage;
	private Map<Integer, User> knownUsers = new HashMap<Integer, User>();
	
	private String listMessagesString = "";
	private String newMessage = "";
	private UIInput newMessageInput = null;

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
		
		this.connectedUsers = userRemote.getConnectedUsers();
		String connectedUsersString = "";
		for( User cur : this.connectedUsers ) {
			if(cur.getUsrId() != currentUser.getUsrId())
				connectedUsersString += "<div class='chat-element' pid='" + cur.getUsrId() + "' pfname='" + cur.getUsrFirstname() + "' plname='" + cur.getUsrLastname() + "'>" + cur.getUsrFirstname() + " " + cur.getUsrLastname() + "</div>" + "<br/>";
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
		this.lastMessage = mess;
		this.newMessage = "";
		this.newMessageInput.setValue(null);
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
		if(lastMessage != null) {
			for(Message mess : messageRemote.findOlderMessages(lastMessage.getUsrReceiverId(), lastMessage.getUsrSenderId()))
				if(!this.listMessages.contains(mess))
					this.listMessages.add(mess);
		}
		
		Collections.sort(this.listMessages);
		Collections.reverse(this.listMessages);
		
		for(Message mess : this.listMessages) {
			
			if(!this.knownUsers.containsKey(mess.getUsrSenderId()))
				this.knownUsers.put(mess.getUsrSenderId(),userRemote.findUser(mess.getUsrSenderId()));
			this.listMessagesString += "<strong>" + knownUsers.get(mess.getUsrSenderId()).getUsrFirstname() + "</strong>" + " : " + mess.getMsgContent() + "<br/>";
				
			if(mess.getMsgIsread() == 0) {
				if(mess.getUsrSenderId() != currentUser.getUsrId()) {
					mess.setMsgIsread((byte)1);
					messageRemote.update(mess);
				}
			}
		}
		
		return listMessagesString;
	}
	
	public void refreshMessages(ActionEvent evt) {
		if(lastMessage == null) {
			lastMessage = new Message();
			lastMessage.setUsrReceiverId(Integer.parseInt((String)FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("receiverid")));
			lastMessage.setUsrSenderId(currentUser.getUsrId());
		}
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

	public UIInput getNewMessageInput() {
		return newMessageInput;
	}

	public void setNewMessageInput(UIInput newMessageInput) {
		this.newMessageInput = newMessageInput;
	}
}