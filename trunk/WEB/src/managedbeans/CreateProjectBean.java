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
	
	public void updateProject()
	{
		Project project = new Project();
		project.setPrjLabel("Test");
		project.setPrjDateCreated(new Date());
		project.setPrjComment("Un projet de test");
		project.setCusId(1);
		projectRemote.update(project);
	}
}