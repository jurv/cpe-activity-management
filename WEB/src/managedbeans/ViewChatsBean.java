package managedbeans;


import java.util.ArrayList;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import model.Message;
import model.Project;
import model.User;

import com.cpeeterprise.BeanMessageRemote;
import com.cpeeterprise.BeanProjectRemote;
import com.cpeeterprise.BeanUserRemote;

@ManagedBean
@ViewScoped
public class ViewChatsBean {
	
	@EJB
	public BeanUserRemote userRemote;
	@EJB
	public BeanMessageRemote messageRemote;
	@EJB
	public BeanProjectRemote projectRemote;
	
	private User currentUser;
	private User currentUserChatter;
	private Project currentProject;
	private ArrayList<User> usersList = new ArrayList<User>();
	
	public void initPage() {

		// Récupération de l'utilisateur communiquant
		String param = (String)FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("usrChatterId") ;
		Integer usrChatterId = (param != null && param != "") ? Integer.parseInt(param) : 0;  
		if(usrChatterId > 0)
			this.currentUserChatter = userRemote.findUser(usrChatterId);
		
		// Récupération du projet courant
		param = (String)FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("prjId") ;
		Integer prjId = (param != null && param != "") ? Integer.parseInt(param) : 0;  
		if(usrChatterId > 0)
			this.currentProject = projectRemote.findProject(prjId);
		
		// Récupération des utilisateurs du projets
		this.usersList.clear();
		this.usersList.addAll(this.userRemote.findUserWithChatConv(this.currentUserChatter.getUsrId(), this.currentProject.getPrjId()));
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

	public Project getCurrentProject() {
		return currentProject;
	}

	public void setCurrentProject(Project currentProject) {
		this.currentProject = currentProject;
	}

	public ArrayList<User> getUsersList() {
		return usersList;
	}

	public void setUsersList(ArrayList<User> usersList) {
		this.usersList = usersList;
	}
}