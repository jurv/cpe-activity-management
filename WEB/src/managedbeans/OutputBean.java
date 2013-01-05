package managedbeans;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import model.Project;


import com.cpeeterprise.BeanProjectRemote;

@ManagedBean(name="project")
@SessionScoped
public class OutputBean {
	
	@EJB
	public BeanProjectRemote projectRemote;
	public Project test;
	
	private List<Project> refProjects;

	public List<Project> getProjectList() {
		if (refProjects == null) {
			refProjects = projectRemote.findProjects();
		}
		return refProjects;
	}
}