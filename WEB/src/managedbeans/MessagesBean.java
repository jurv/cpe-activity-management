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
		if(deletedMessagesList.size() == 0 || deletedMessagesList.get(0).getUsrSenderId() != currentUser.getUsrId())
			deletedMessagesList.addAll(messageRemote.findDeletedMessagesFor(currentUser.getUsrId()));
		return deletedMessagesList;
	}

	public void setDeletedMessagesList(ArrayList<Message> deletedMessagesList) {
		this.deletedMessagesList = deletedMessagesList;
	}

	public ArrayList<Message> getReceivedMessagesList() {
		if(receivedMessagesList.size() == 0 || receivedMessagesList.get(0).getUsrSenderId() != currentUser.getUsrId())
			receivedMessagesList.addAll(messageRemote.findReceivedMessagesFor(currentUser.getUsrId()));
		return receivedMessagesList;
	}

	public void setReceivedMessagesList(ArrayList<Message> receivedMessagesList) {
		this.receivedMessagesList = receivedMessagesList;
	}

	public ArrayList<Message> getSentMessagesList() {
		if(sentMessagesList.size() == 0 || sentMessagesList.get(0).getUsrSenderId() != currentUser.getUsrId())
			sentMessagesList.addAll(messageRemote.findSentMessagesFor(currentUser.getUsrId()));
		return sentMessagesList;
	}

	public void setSentMessagesList(ArrayList<Message> sentMessagesList) {
		this.sentMessagesList = sentMessagesList;
	}
}