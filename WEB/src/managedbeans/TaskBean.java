package managedbeans;


import java.util.ArrayList;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import model.Task;
import model.User;
import model.WorkPiece;

import com.cpeeterprise.BeanTaskRemote;
import com.cpeeterprise.BeanUserRemote;
import com.cpeeterprise.BeanWorkPieceRemote;

@ManagedBean
@RequestScoped
public class TaskBean {
	
	@EJB
	public BeanTaskRemote taskRemote;
	@EJB
	public BeanWorkPieceRemote workPieceRemote;
	@EJB
	public BeanUserRemote userRemote;
	private Boolean isLoaded = false;
	private String taskName = "";
	private String taskComment = "";
	private int taskPrjId = 0;
	private int taskDuration = 0;
	private int taskAssignedTo =0 ;
	private int taskId = 0;
	private int taskTstId = 0;
	private User currentUser;
	private ArrayList<WorkPiece> wps = new ArrayList<WorkPiece>();
	
	//Constantes
	final int ID_STA_NOTSTARTED = 1;
	
	public String createTask()
	{
		String nextPage="Tasks";
		
		Task task = new Task();
		
		task.setPrjId(this.taskPrjId);
		task.setTskDateAssigned(new Date());
		task.setTskDateDeadline(new Date());
		task.setTskDescription(this.taskComment);
		task.setTskDuration(this.taskDuration);
		task.setTskLabel(this.taskName);
//		task.setTskLevel();
		task.setTssId(ID_STA_NOTSTARTED);
		task.setUsrAssignedbyId(this.currentUser.getUsrId());
		task.setUser(userRemote.findUser((this.taskAssignedTo)));
		task.setTstId(this.taskTstId);
		
		taskRemote.persist(task);
		
		return nextPage;
	}
		
	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	public int getTaskTstId() {
		return taskTstId;
	}

	public void setTaskTstId(int taskTstId) {
		this.taskTstId = taskTstId;
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
		int tskId = Integer.parseInt((String)FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("tskIdDel"));
		Task tsk = taskRemote.findTask(tskId);
		taskRemote.logicalDelete(tsk);
		return true;
	}
	
	public void loadTask(){
		
		if (!this.isLoaded){
			this.taskId = Integer.parseInt((String)FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("tskIdDet"));
			Task tsk = taskRemote.findTask(this.taskId);
			//this.taskAssignedTo = tsk.getUser().getUsrId();
			this.taskComment    = tsk.getTskDescription();
			this.taskDuration   = tsk.getTskDuration();
			this.taskName       = tsk.getTskLabel();
			this.taskPrjId      = tsk.getPrjId();
			
			if(this.getWps().size() == 0) {
				this.getWps().clear();
				this.getWps().addAll(workPieceRemote.findWorkPiecesByTask(this.taskId));
			}
			this.isLoaded = true;
		}
	}

	public ArrayList<WorkPiece> getWps() {
		return wps;
	}

	public void setWps(ArrayList<WorkPiece> wps) {
		this.wps = wps;
	}
}