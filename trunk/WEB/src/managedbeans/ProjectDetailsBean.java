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

	
	private Project currentProject;
	
	private List<Task> notStartedTasks;
	private List<Task> startedTasks;
	private List<Task> canceledTasks;
	private List<Task> validatedTasks;
	private List<Task> inProgressTasks;
	private List<Task> finishedTasks;
	
	public void initPage()
	{
		// Récupération du projet courant 
		String param = (String)FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("prjIdDet") ;
		Integer prjId = (param != null && param != "") ? Integer.parseInt(param) : 0;  
		if(prjId > 0)
			this.currentProject = projectRemote.findProject(prjId);
	}
	public Project getCurrentProject() {
		return this.currentProject;
	}

	public void setCurrentProject(Project currentProject) {
		this.currentProject = currentProject;
	}

	public List<Task> getNotStartedTasks() {
		return taskRemote.findTasksByProject(this.currentProject.getPrjId(),ID_STA_NOTSTARTED);
	}

	public void setNotStartedTasks(List<Task> tasks) {
		this.notStartedTasks = tasks;
	}
	
	public List<Task> getStartedTasks() {
		return taskRemote.findTasksByProject(this.currentProject.getPrjId(),ID_STA_STARTED);
	}

	public void setStartedTasks(List<Task> tasks) {
		this.startedTasks = tasks;
	}
	
	public List<Task> getInProgressTasks() {
		return taskRemote.findTasksByProject(this.currentProject.getPrjId(),ID_STA_INPROGRESS);
	}

	public void setInProgressTasks(List<Task> tasks) {
		this.inProgressTasks = tasks;
	}
	
	public List<Task> getCanceledTasks() {
		return taskRemote.findTasksByProject(this.currentProject.getPrjId(),ID_STA_CANCELED);
	}

	public void setCanceledTasks(List<Task> tasks) {
		this.canceledTasks = tasks;
	}
	public List<Task> getFinishedTasks() {
		return taskRemote.findTasksByProject(this.currentProject.getPrjId(),ID_STA_CANCELED);
	}

	public void setFinishedTasks(List<Task> tasks) {
		this.finishedTasks = tasks;
	}
	public List<Task> getValidatedTasks() {
		return taskRemote.findTasksByProject(this.currentProject.getPrjId(),ID_STA_CANCELED);
	}

	public void setValidatedTasks(List<Task> tasks) {
		this.validatedTasks = tasks;
	}
}
