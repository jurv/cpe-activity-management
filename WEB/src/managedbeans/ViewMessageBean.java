package managedbeans;


import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import model.Message;
import model.User;

import com.cpeeterprise.BeanMessageRemote;
import com.cpeeterprise.BeanProjectRemote;
import com.cpeeterprise.BeanUserRemote;

@ManagedBean
@ViewScoped
public class ViewMessageBean {
	
	@EJB
	public BeanUserRemote userRemote;
	@EJB
	public BeanMessageRemote messageRemote;
	@EJB
	public BeanProjectRemote projectRemote;
	private User currentUser;
	private Message currentMessage;
	private String msgSenderName;
	private String msgReceiverName;
	private String msgProjectName;
	
	
	public void initPage() {
		this.currentMessage = messageRemote.findMessage(Integer.parseInt((String)FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("msgid")));
		this.msgProjectName = projectRemote.findProject(currentMessage.getPrjId()).getPrjLabel();
		User receiver = userRemote.findUser(this.currentMessage.getUsrReceiverId());
		this.msgReceiverName = receiver.getUsrFirstname() + " " + receiver.getUsrLastname();
		User sender = userRemote.findUser(this.currentMessage.getUsrSenderId());
		this.msgSenderName = sender.getUsrFirstname() + " " + sender.getUsrLastname();
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public String getMsgSenderName() {
		return msgSenderName;
	}

	public void setMsgSenderName(String msgSenderName) {
		this.msgSenderName = msgSenderName;
	}

	public String getMsgReceiverName() {
		return msgReceiverName;
	}

	public void setMsgReceiverName(String msgReceiverName) {
		this.msgReceiverName = msgReceiverName;
	}

	public String getMsgProjectName() {
		return msgProjectName;
	}

	public void setMsgProjectName(String msgProjectName) {
		this.msgProjectName = msgProjectName;
	}

	public Message getCurrentMessage() {
		return currentMessage;
	}

	public void setCurrentMessage(Message currentMessage) {
		this.currentMessage = currentMessage;
	}
}