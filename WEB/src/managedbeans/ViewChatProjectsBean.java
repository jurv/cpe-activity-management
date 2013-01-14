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
public class ViewChatProjectsBean {
	
	@EJB
	public BeanUserRemote userRemote;
	@EJB 
	public BeanProjectRemote projectRemote;
	private Project currentProject;
	private User currentUser = new User();	
	public void initView() {
		
		// Récupération du projet
		this.currentProject = projectRemote.findProject(Integer.parseInt((String)FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("prjId")));
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

}