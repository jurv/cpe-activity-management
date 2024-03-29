package managedbeans;


import java.util.ArrayList;
import java.util.Map;

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
public class ViewChatProjectsBean {
	
	@EJB
	public BeanUserRemote userRemote;
	@EJB 
	public BeanProjectRemote projectRemote;
	private Project currentProject;
	private User currentUser = new User();	
	private ArrayList<User> chatUsers = new ArrayList<User>();
	public void initView() {
		
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		
		// Récupération du projet
		String param = (String)params.get("prjId") ;
		Integer prjId = (param != null && param != "") ? Integer.parseInt(param) : 0;  
		if(prjId > 0)
			this.currentProject = projectRemote.findProject(prjId);
		
		// On tente de récupérer l'id de l'utilisateur pour lequel on veut afficher les conversations
		// Si on n'y arrive pas on prend l'utilisateur courant
		param = (String)params.get("usrChatterId") ;
		Integer usrChatterId =  (param != null && param != "") ? Integer.parseInt(param) : 0; 
		this.getChatUsers().clear();
		if(usrChatterId > 0)
			this.getChatUsers().addAll(userRemote.findUserWithChatConv(usrChatterId, this.currentProject.getPrjId()));
		else {
			// Récupération des utilisateurs avec lesquels j'ai communiqué sur ce projet
			this.getChatUsers().addAll(userRemote.findUserWithChatConv(currentUser.getUsrId(), this.currentProject.getPrjId()));
		}
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