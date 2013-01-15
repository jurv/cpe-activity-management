package managedbeans;


import java.util.ArrayList;

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
public class ViewChatBean {
	
	@EJB
	public BeanUserRemote userRemote;
	@EJB
	public BeanMessageRemote messageRemote;
	@EJB
	public BeanProjectRemote projectRemote;
	
	private User currentUser;
	private User currentUserChatter;
	private ArrayList<Message> messagesList = new ArrayList<Message>();
	
	
	public void initPage() {

		// Récupération de l'utilisateur communiquant
		String param = (String)FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("usrChatterId") ;
		Integer usrChatterId = (param != null && param != "") ? Integer.parseInt(param) : 0;  
		if(usrChatterId > 0)
			this.currentUserChatter = userRemote.findUser(usrChatterId);
		
		// Récupération de l'utilisateur source, si celui-ci n'existe pas, 
		// on prendra l'utilisateur courant
		param = (String)FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("usrSourceId") ;
		Integer usrSourceId = (param != null && param != "") ? Integer.parseInt(param) : 0; 
	
		// Récupération des messages
		this.getMessagesList().clear();
		this.getMessagesList().addAll(messageRemote.findMessagesForConv(((usrSourceId > 0)? usrSourceId : this.currentUser.getUsrId()), this.currentUserChatter.getUsrId()));
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public User getCurrentUserChatter() {
		return currentUserChatter;
	}

	public void setCurrentUserChatter(User currentUserChatter) {
		this.currentUserChatter = currentUserChatter;
	}

	public ArrayList<Message> getMessagesList() {
		return messagesList;
	}

	public void setMessagesList(ArrayList<Message> messagesList) {
		this.messagesList = messagesList;
	}
}