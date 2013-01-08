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
	
	public void updateProject()
	{
		Project project = new Project();
		project.setPrjLabel("Test");
		project.setPrjDateCreated(new Date());
		project.setPrjComment("Un projet de test");
		project.setCusId(1);
		projectRemote.update(project);
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