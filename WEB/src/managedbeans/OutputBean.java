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
import model.Function;
import model.Project;
import model.Task;
import model.User;


import com.cpeeterprise.BeanCustomerRemote;
import com.cpeeterprise.BeanProjectRemote;
import com.cpeeterprise.BeanTaskRemote;
import com.cpeeterprise.BeanUserRemote;
import com.cpeeterprise.BeanFunctionRemote;

@ManagedBean(name="output")
@RequestScoped
public class OutputBean {
	
	@EJB
	public BeanProjectRemote projectRemote;
	
	@EJB
	public BeanCustomerRemote customerRemote;
	
	@EJB
	public BeanTaskRemote taskRemote;
	@EJB
	public BeanUserRemote userRemote;
	
	@EJB
	public BeanFunctionRemote functionRemote;
	
	
	public Project test;//a QUOI CA SERT??? => certainement rien du tout...
	
	private List<Project> refProjects;
	private List<Project> refActiveProjects;
	private List<Task> refTasks;
	private List<Task> refActiveTasks;
	private List<Customer> refCustomers;
	private List<User> refUsers;
	private List<Function> refFunctions;
	

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
	
	public List<Task> getTaskList() {
		if (refTasks == null) {
			refTasks = taskRemote.findTasks();
		}
		return refTasks;
	}
	
	public List<Task> getActiveTaskList() {
		if (refActiveTasks == null) {
			refActiveTasks = taskRemote.findActiveTasks();
		}
		return refActiveTasks;
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
	public List<User> getUserList(){
		if(refUsers == null){
			refUsers = userRemote.findUsers();
		}
		return refUsers;
	}
	public List<User> getActiveUserList(){
		if(refUsers == null){
			refUsers = userRemote.findActiveUsers();
		}
		return refUsers;
	}
	public List<User> getFunctionList(){
		if(refFunctions == null){
			refFunctions = functionRemote.findFunction();
		}
		return refUsers;
	}
}