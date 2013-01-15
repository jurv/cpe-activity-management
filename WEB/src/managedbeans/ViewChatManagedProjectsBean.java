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
public class ViewChatManagedProjectsBean {
	
	@EJB
	public BeanUserRemote userRemote;
	@EJB 
	public BeanProjectRemote projectRemote;
	private Project currentProject;
	private User currentUser = new User();	
	private ArrayList<User> chatUsers = new ArrayList<User>();
	public void initView() {
		
		// Récupération du projet
		String param = (String)FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("prjId") ;
		Integer prjId = (param != null && param != "") ? Integer.parseInt(param) : 0;  
		if(prjId > 0)
			this.currentProject = projectRemote.findProject(prjId);
		
		// Récupération des utilisateurs ayant communiqué pour ce projet
		this.getChatUsers().clear();
		this.getChatUsers().addAll(userRemote.findUsersByProject(prjId));
	}
	
	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public Project getCurrentProject() {
		return currentProject;
	}

	public void setCurrentProject(Project currentProject) {
		this.currentProject = currentProject;
	}

	public ArrayList<User> getChatUsers() {
		return chatUsers;
	}

	public void setChatUsers(ArrayList<User> chatUsers) {
		this.chatUsers = chatUsers;
	}

}