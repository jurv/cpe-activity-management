package managedbeans;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import model.Customer;
import model.Project;


import com.cpeeterprise.BeanCustomerRemote;
import com.cpeeterprise.BeanProjectRemote;

@ManagedBean(name="output")
@RequestScoped
public class OutputBean {
	
	@EJB
	public BeanProjectRemote projectRemote;
	
	@EJB
	public BeanCustomerRemote customerRemote;
	
	
	public Project test;//a QUOI CA SERT??? => certainement rien du tout...
	
	private List<Project> refProjects;
	private List<Project> refActiveProjects;
	private List<Customer> refCustomers;
	

	public List<Project> getProjectList() {
		if (refProjects == null) {
			refProjects = projectRemote.findProjects();
		}
		return refProjects;
	}
	
	public List<Project> getActiveProjectList() {
		if (refActiveProjects == null) {
			refActiveProjects = projectRemote.findActiveProjects();
		}
		return refActiveProjects;
	}
	
	public List<Customer> getCustomerList() {
		if (refCustomers == null) {
			refCustomers = customerRemote.findCustomers();
		}
		return refCustomers;
	}
	public List<Customer> getActiveCustomerList() {
		if (refCustomers == null) {
			refCustomers = customerRemote.findActiveCustomers();
		}
		return refCustomers;
	}
}