package managedbeans;


import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import model.Task;

import com.cpeeterprise.BeanTaskRemote;

@ManagedBean
@SessionScoped
public class TaskBean {
	
	@EJB
	public BeanTaskRemote taskRemote;
	
	private String taskName = "";
	private String taskComment = "";
	private int taskPrjId = 0;
	private int taskDuration = 0;
	private int taskAssignedTo = 0;
	
	public void createTask()
	{
		Task task = new Task();
		
		task.setPrjId(this.taskPrjId);
		task.setTskDateAssigned(new Date());
		task.setTskDateDeadline(new Date());
		task.setTskDescription(this.taskComment);
		task.setTskDuration(this.taskDuration);
		task.setTskLabel(this.taskName);
//		task.setTskLevel();
		task.setUsrAssignedbyId(1);
		task.setUsrAssignedtoId(this.taskAssignedTo);
		
		taskRemote.persist(task);
	}
		
	public BeanTaskRemote getTaskRemote() {
		return taskRemote;
	}



	public void setTaskRemote(BeanTaskRemote taskRemote) {
		this.taskRemote = taskRemote;
	}



	public String getTaskName() {
		return taskName;
	}



	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}



	public String getTaskComment() {
		return taskComment;
	}



	public void setTaskComment(String taskComment) {
		this.taskComment = taskComment;
	}



	public int getTaskPrjId() {
		return taskPrjId;
	}



	public void setTaskPrjId(int taskPrjId) {
		this.taskPrjId = taskPrjId;
	}



	public int getTaskDuration() {
		return taskDuration;
	}



	public void setTaskDuration(int taskDuration) {
		this.taskDuration = taskDuration;
	}



	public int getTaskAssignedTo() {
		return taskAssignedTo;
	}



	public void setTaskAssignedTo(int taskAssignedTo) {
		this.taskAssignedTo = taskAssignedTo;
	}



	public boolean deleteTask() {
		int tskId = Integer.parseInt((String)FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("prjIdDel"));
		Task tsk = taskRemote.findTask(tskId);
		taskRemote.logicalDelete(tsk);
		return true;
	}
}