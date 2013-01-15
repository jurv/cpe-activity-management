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
public class ChatsProjectBean {
	
	@EJB
	public BeanUserRemote userRemote;
	@EJB
	public BeanProjectRemote projectRemote;
	
	private User currentUser = new User();	
	private ArrayList <Project> managedProject = new ArrayList<Project>();
	private ArrayList <Project> ownProject = new ArrayList<Project>();

	public void initView() {
		// Récupération de la liste des projets de l'utilisateur
		this.ownProject.clear();
		this.ownProject.addAll(projectRemote.findProjectsByUser(this.currentUser.getUsrId()));
		
		// Récupération de la liste des projets pour lesquels l'utilisateur a un role de management
		this.managedProject.clear();
		this.managedProject.addAll(projectRemote.findManagedProjectsByUser(this.currentUser.getUsrId()));
	}
	
	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public ArrayList <Project> getManagedProject() {
		return managedProject;
	}

	public void setManagedProject(ArrayList <Project> managedProject) {
		this.managedProject = managedProject;
	}

	public ArrayList <Project> getOwnProject() {
		return ownProject;
	}

	public void setOwnProject(ArrayList <Project> ownProject) {
		this.ownProject = ownProject;
	}
}