package managedbeans;


import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import model.Task;
import model.User;
import model.WorkPiece;

import com.cpeeterprise.BeanTaskRemote;
import com.cpeeterprise.BeanWorkPiece;
import com.cpeeterprise.BeanWorkPieceRemote;

@ManagedBean
@SessionScoped
public class WorkPieceBean {
	
	@EJB
	public BeanWorkPieceRemote workPieceRemote;
	
	@EJB
	public BeanTaskRemote taskRemote;
	
	private String workPieceComment = "";
	private int workPieceTskId = 0;
	private int taskTssId = 0;
	private int workPieceUsrId = 0;
	private Date workPieceDate = new Date();
	private int workPieceDuration = 0;
	
	
	private User currentUser = new User();	
	
	
	public BeanWorkPieceRemote getWorkPieceRemote() {
		return workPieceRemote;
	}



	public void setWorkPieceRemote(BeanWorkPieceRemote workPieceRemote) {
		this.workPieceRemote = workPieceRemote;
	}



	public String getWorkPieceComment() {
		return workPieceComment;
	}



	public void setWorkPieceComment(String workPieceComment) {
		this.workPieceComment = workPieceComment;
	}



	public int getWorkPieceTskId() {
		return workPieceTskId;
	}



	public void setWorkPieceTskId(int workPieceTskId) {
		this.workPieceTskId = workPieceTskId;
	}



	public int getWorkPieceUsrId() {
		return workPieceUsrId;
	}



	public void setWorkPieceUsrId(int workPieceUsrId) {
		this.workPieceUsrId = workPieceUsrId;
	}



	public Date getWorkPieceDate() {
		return workPieceDate;
	}



	public void setWorkPieceDate(Date workPieceDate) {
		this.workPieceDate = workPieceDate;
	}



	public int getWorkPieceDuration() {
		return workPieceDuration;
	}

	public void setWorkPieceDuration(int workPieceDuration) {
		this.workPieceDuration = workPieceDuration;
	}

	public int getTaskTssId() {
		return taskTssId;
	}

	public void setTaskTssId(int workPieceTssId) {
		this.taskTssId = workPieceTssId;
	}

	public void createWorkPiece()
	{
		this.workPieceUsrId = this.currentUser.getUsrId();
		
		WorkPiece workPiece = new WorkPiece();
		workPiece.setTskId(this.workPieceTskId);
		workPiece.setUsrId(this.workPieceUsrId);
		workPiece.setWrkComment(this.workPieceComment);
		workPiece.setWrkDate(this.workPieceDate);
		workPiece.setWrkDuration(this.workPieceDuration);
			
		workPieceRemote.persist(workPiece);
		
		Task task = taskRemote.findTask(this.workPieceTskId);
		task.setTssId(this.taskTssId);
		taskRemote.update(task);
	}	
		
	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
}