package managedbeans;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import model.Message;
import model.Project;
import model.Task;
import model.User;


import com.cpeeterprise.BeanMessageRemote;
import com.cpeeterprise.BeanProjectRemote;
import com.cpeeterprise.BeanTaskRemote;
import com.cpeeterprise.BeanUserRemote;

@ManagedBean
@SessionScoped
public class ProjectDetailsBean {
	
	@EJB
	public BeanProjectRemote projectRemote;
	@EJB
	public BeanTaskRemote taskRemote;
	
	// Constantes
	final int ID_STA_NOTSTARTED = 1;
	final int ID_STA_STARTED = 2;
	final int ID_STA_FINISHED = 3; 
	final int ID_STA_VALIDATED = 4;
	final int ID_STA_CANCELED = 5;
	final int ID_STA_INPROGRESS = 6; 
	
	// Current Project
	private Project currentProject;
	
	// Tasks List
	private List<Task> notStartedTasks = new ArrayList<Task>();
	private List<Task> startedTasks = new ArrayList<Task>();;
	private List<Task> canceledTasks = new ArrayList<Task>();;
	private List<Task> validatedTasks = new ArrayList<Task>();;
	private List<Task> inProgressTasks = new ArrayList<Task>();;
	private List<Task> finishedTasks = new ArrayList<Task>();;
	private List<Task> allTasks = new ArrayList<Task>();;
	
	public void initPage()
	{
		// Récupération du projet courant 
		String param = (String)FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("prjIdDet") ;
		Integer prjId = (param != null && param != "") ? Integer.parseInt(param) : 0;  
		if(prjId > 0)
			this.currentProject = projectRemote.findProject(prjId);
		
		//Récupération des Tâches de l'objet courant
		this.allTasks = taskRemote.findTasksByProject(this.currentProject.getPrjId());
		for(Task t : this.allTasks){
			switch(t.getTssId()){
			case ID_STA_NOTSTARTED :
				this.notStartedTasks.add(t);
				break;
			case ID_STA_STARTED:
				this.startedTasks.add(t);
				break;
			case ID_STA_FINISHED:
				this.startedTasks.add(t);
				break;
			case ID_STA_VALIDATED:
				this.validatedTasks.add(t);
				break;
			case ID_STA_CANCELED:
				this.canceledTasks.add(t);
				break;
			case ID_STA_INPROGRESS:
				this.inProgressTasks.add(t);
				break;
			default:
				break;
			}
		}
	}
	
	public Project getCurrentProject() {
		return this.currentProject;
	}

	public void setCurrentProject(Project currentProject) {
		this.currentProject = currentProject;
	}

	public List<Task> getNotStartedTasks() {
		return this.notStartedTasks;
	}

	public void setNotStartedTasks(List<Task> tasks) {
		this.notStartedTasks = tasks;
	}
	
	public List<Task> getStartedTasks() {
		return this.startedTasks;
	}

	public void setStartedTasks(List<Task> tasks) {
		this.startedTasks = tasks;
	}
	
	public List<Task> getInProgressTasks() {
		return this.inProgressTasks;
	}

	public void setInProgressTasks(List<Task> tasks) {
		this.inProgressTasks = tasks;
	}
	
	public List<Task> getCanceledTasks() {
		return this.canceledTasks;
	}

	public void setCanceledTasks(List<Task> tasks) {
		this.canceledTasks = tasks;
	}
	public List<Task> getFinishedTasks() {
		return this.finishedTasks;
	}

	public void setFinishedTasks(List<Task> tasks) {
		this.finishedTasks = tasks;
	}
	public List<Task> getValidatedTasks() {
		return this.canceledTasks;
	}

	public void setValidatedTasks(List<Task> tasks) {
		this.validatedTasks = tasks;
	}
}
