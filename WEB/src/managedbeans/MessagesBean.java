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
	private ArrayList<Message> messagesList = new ArrayList<Message>();


	public ArrayList<Message> getMessagesList() {
		return messagesList;
	}

	public void setMessagesList(ArrayList<Message> messagesList) {
		this.messagesList = messagesList;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
}