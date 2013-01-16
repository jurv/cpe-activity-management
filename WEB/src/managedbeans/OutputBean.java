package managedbeans;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

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

	public List<Project> getProjectList() {
		return projectRemote.findProjects();
	}
	
	public List<Project> getActiveProjectList() {
		return projectRemote.findActiveProjects();
	}
	
	public List<Task> getTaskList() {
		return taskRemote.findTasks();
	}
	
	public List<Task> getActiveTaskList() {
		return taskRemote.findActiveTasks();
	}
	
	public List<Customer> getCustomerList() {
		return customerRemote.findCustomers();
	}
	public List<Customer> getActiveCustomerList() {
		return customerRemote.findActiveCustomers();
	}
	public List<User> getUserList(){
		return userRemote.findUsers();
	}
	public List<User> getActiveUserList(){
		return userRemote.findActiveUsers();
	}
	public List<Function> getFunctionList(){
		return functionRemote.findFunction();
	}
}