package managedbeans;


import java.util.ArrayList;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import model.Message;
import model.User;


import com.cpeeterprise.BeanMessageRemote;
import com.cpeeterprise.BeanUserRemote;

@ManagedBean
@SessionScoped
public class MessagesBean {
	
	@EJB
	public BeanUserRemote userRemote;
	@EJB
	public BeanMessageRemote messageRemote;
	private User currentUser = new User();	
	private ArrayList<Message> deletedMessagesList = new ArrayList<Message>();
	private ArrayList<Message> receivedMessagesList = new ArrayList<Message>();
	private ArrayList<Message> sentMessagesList = new ArrayList<Message>();

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public ArrayList<Message> getDeletedMessagesList() {
		
		return deletedMessagesList;
	}

	public void setDeletedMessagesList(ArrayList<Message> deletedMessagesList) {
		this.deletedMessagesList = deletedMessagesList;
	}

	public ArrayList<Message> getReceivedMessagesList() {
		
		return receivedMessagesList;
	}

	public void setReceivedMessagesList(ArrayList<Message> receivedMessagesList) {
		this.receivedMessagesList = receivedMessagesList;
	}

	public ArrayList<Message> getSentMessagesList() {
		
		return sentMessagesList;
	}

	public void setSentMessagesList(ArrayList<Message> sentMessagesList) {
		this.sentMessagesList = sentMessagesList;
	}
	
	public void fillMessagesLists () {
		
		if(sentMessagesList.size() == 0 || sentMessagesList.get(0).getSender().getUsrId() != currentUser.getUsrId()) {
			sentMessagesList.clear();
			sentMessagesList.addAll(messageRemote.findSentMessagesFor(currentUser.getUsrId()));
		}
		
		if(receivedMessagesList.size() == 0 || receivedMessagesList.get(0).getReceiver().getUsrId() != currentUser.getUsrId()){
			receivedMessagesList.clear();
			receivedMessagesList.addAll(messageRemote.findReceivedMessagesFor(currentUser.getUsrId()));
		}
		
		if(deletedMessagesList.size() == 0 || deletedMessagesList.get(0).getSender().getUsrId() != currentUser.getUsrId()) {
			deletedMessagesList.clear();
			deletedMessagesList.addAll(messageRemote.findDeletedMessagesFor(currentUser.getUsrId()));
		}
	}
	
	public String deleteMessage() {
		int tabN = Integer.parseInt((String)FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("tabN"));
		int msgId = Integer.parseInt((String)FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("msgId"));
		Message msg = messageRemote.findMessage(msgId);
		
		messageRemote.logicalDelete(msg);
		
		if(tabN == 1) {
			receivedMessagesList.remove(msg);
		}
		else if(tabN == 2) {
			sentMessagesList.remove(msg);
		}
		else if(tabN == 3) {
			deletedMessagesList.remove(msg);
		}
		
		return null;
	}
}