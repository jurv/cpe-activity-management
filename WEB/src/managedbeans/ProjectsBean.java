package managedbeans;


import java.util.ArrayList;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.Project;
import com.cpeeterprise.BeanProjectRemote;

@ManagedBean
@SessionScoped
public class ProjectsBean {
	
	@EJB
	public BeanProjectRemote projectRemote;
	
		
	public boolean deleteProject() {
		int prjId = Integer.parseInt((String)FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("prjIdDel"));
		Project prj = projectRemote.findProject(prjId);
		projectRemote.logicalDelete(prj);
		return true;
	}
}