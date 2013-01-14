package managedbeans;


import java.util.ArrayList;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import model.Message;
import model.Project;
import model.User;


import com.cpeeterprise.BeanMessageRemote;
import com.cpeeterprise.BeanProjectRemote;
import com.cpeeterprise.BeanUserRemote;

@ManagedBean
@SessionScoped
public class ChatsBean {
	
	@EJB
	public BeanUserRemote userRemote;
	@EJB
	public BeanMessageRemote messageRemote;
	@EJB
	public BeanProjectRemote projectRemote;
	
	private User currentUser = new User();	
	private ArrayList <User> userWithChat = new ArrayList<User>(); 
	private ArrayList <Project> managedProject = new ArrayList<Project>();

	public void initView() {
		this.userWithChat.clear();
		this.userWithChat.addAll(userRemote.findUserWithChatConv(this.currentUser.getUsrId()));
	}
	
	public void fillLists () {
		
	}
	
	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public ArrayList <User> getUserWithChat() {
		return userWithChat;
	}

	public void setUserWithChat(ArrayList <User> userWithChat) {
		this.userWithChat = userWithChat;
	}

	public ArrayList <Project> getManagedProject() {
		return managedProject;
	}

	public void setManagedProject(ArrayList <Project> managedProject) {
		this.managedProject = managedProject;
	}
}