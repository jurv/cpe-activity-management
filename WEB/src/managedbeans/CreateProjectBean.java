package managedbeans;

import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.Project;
import com.cpeeterprise.BeanProjectRemote;

@ManagedBean
@SessionScoped
public class CreateProjectBean {
	
	@EJB
	public BeanProjectRemote projectRemote;
	private String projectName = "";
	private String projectTotalTime = "";
	private Date projectDateCreated = new Date();
	private Date projectDateFinished = new Date();
	
	public void updateProject()
	{
		Project project = new Project();
		project.setPrjLabel(this.projectName);
		project.setPrjDateCreated(this.projectDateCreated);
		project.setPrjDateFinished(this.projectDateFinished);
		project.setPrjComment("Un projet");
		project.setCusId(1);
		projectRemote.update(project);
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

	public String getProjectTotalTime() {
		return projectTotalTime;
	}

	public void setProjectTotalTime(String projectTotalTime) {
		this.projectTotalTime = projectTotalTime;
	}
	
}