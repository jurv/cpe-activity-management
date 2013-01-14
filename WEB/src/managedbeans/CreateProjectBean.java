package managedbeans;

import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.cpeeterprise.BeanProjectRemote;
import com.cpeeterprise.BeanUser2ProjectRemote;

import model.Project;
import model.User2Project;

@ManagedBean
@SessionScoped
public class CreateProjectBean {
	
	@EJB
	public BeanProjectRemote projectRemote;
	@EJB
	public BeanUser2ProjectRemote user2projectRemote;
	
	private String projectName = "";
	private String projectComment = "";
	private Date projectDateCreated = new Date();
	private Date projectDateFinished = new Date();
	private int projectTotalTime = 0;
	private int projectCusId = 0;
	private int projectCdpId = 0;
	
	public void updateProject()
	{
		Project project = new Project();
		User2Project user2project = new User2Project();
		
		project.setPrjLabel(this.projectName);
		project.setPrjDateCreated(this.projectDateCreated);
		project.setPrjDateFinished(this.projectDateFinished);
		project.setPrjComment(this.projectComment);
		project.setCusId(this.projectCusId);
		project.setPrjNbDays(this.projectTotalTime);
		project = projectRemote.persist(project);
		
		user2project.setPrjId(project.getPrjId());
		user2project.setFctId(2);
		user2project.setUsrId(this.projectCdpId);
		user2projectRemote.persist(user2project);
	}

	public int getProjectCusId() {
		return this.projectCusId;
	}

	public void setProjectCusId(int c) {
		this.projectCusId = c;
	}

	public int getProjectCdpId() {
		return projectCdpId;
	}

	public void setProjectCdpId(int projectCdpId) {
		this.projectCdpId = projectCdpId;
	}

	public String getProjectComment() {
		return projectComment;
	}

	public void setProjectComment(String projectComment) {
		this.projectComment = projectComment;
	}

	public Date getProjectDateCreated() {
		return projectDateCreated;
	}

	public void setProjectDateCreated(Date projectDateCreated) {
		this.projectDateCreated = projectDateCreated;
	}

	public Date getProjectDateFinished() {
		return projectDateFinished;
	}

	public void setProjectDateFinished(Date projectDateFinished) {
		this.projectDateFinished = projectDateFinished;
	}
	
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public int getProjectTotalTime() {
		return projectTotalTime;
	}

	public void setProjectTotalTime(int projectTotalTime) {
		this.projectTotalTime = projectTotalTime;
	}
	
}