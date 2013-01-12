package managedbeans;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import model.Customer;
import model.Project;


import com.cpeeterprise.BeanCustomerRemote;
import com.cpeeterprise.BeanProjectRemote;

@ManagedBean(name="output")
@SessionScoped
public class OutputBean {
	
	@EJB
	public BeanProjectRemote projectRemote;
	
	@EJB
	public BeanCustomerRemote customerRemote;
	
	
	public Project test;//a QUOI CA SERT??? => certainement rien du tout...
	
	private List<Project> refProjects;
	private List<Customer> refCustomers;
	

	public List<Project> getProjectList() {
		if (refProjects == null) {
			refProjects = projectRemote.findProjects();
		}
		return refProjects;
	}
	
	public List<Customer> getCustomerList() {
		if (refCustomers == null) {
			refCustomers = customerRemote.findCustomers();
		}
		return refCustomers;
	}
}