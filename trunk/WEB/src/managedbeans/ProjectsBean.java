package managedbeans;


import java.util.ArrayList;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import model.Message;
import model.Project;
import model.User;


import com.cpeeterprise.BeanMessageRemote;
import com.cpeeterprise.BeanProjectRemote;
import com.cpeeterprise.BeanUserRemote;

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