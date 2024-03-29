package managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import model.Customer;
import model.Project;
import model.Function;
import model.TaskStatus;
import model.TaskType;
import model.User;
import model.WorkPiece;
import model.Task;

import com.cpeeterprise.BeanCustomerRemote;

import com.cpeeterprise.BeanProjectRemote;
import com.cpeeterprise.BeanFunctionRemote;
import com.cpeeterprise.BeanTaskRemote;
import com.cpeeterprise.BeanTaskStatusRemote;
import com.cpeeterprise.BeanTaskTypeRemote;
import com.cpeeterprise.BeanUserRemote;
import com.cpeeterprise.BeanWorkPieceRemote;

@ManagedBean(name="input")
@SessionScoped
public class InputBean {
	
	@EJB
	public BeanWorkPieceRemote workPieceRemote;
	
	@EJB
	public BeanCustomerRemote customerRemote;
	
	@EJB
	public BeanProjectRemote projectRemote;
	
	@EJB
	public BeanUserRemote userRemote;
	
	@EJB
	public BeanFunctionRemote functionRemote;
	
	@EJB
	public BeanTaskRemote taskRemote;

	@EJB
	public BeanTaskTypeRemote taskTypeRemote;
	
	@EJB
	public BeanTaskStatusRemote taskStatusRemote;
	

	
	private List<SelectItem> customersItems = new ArrayList<SelectItem>();
	
	// Liste des users ayant un profil chef de projet
	private List<SelectItem> cdpItems = new ArrayList<SelectItem>();
	
	private List<SelectItem> usersItems = new ArrayList<SelectItem>();
	
	private List<SelectItem> projectsItems = new ArrayList<SelectItem>();
	
	private List <SelectItem> myTasksItems = new ArrayList<SelectItem>();
	
	private List<SelectItem> funcItems = new ArrayList<SelectItem>();
	
	private List<SelectItem> tstItems = new ArrayList<SelectItem>();
	
	private List<SelectItem> tssItems = new ArrayList<SelectItem>();
	
	
	private User currentUser = new User();	
	
	
	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	
	public List<SelectItem> getMyTasksItems(){
		int usrId = currentUser.getUsrId();
		System.out.println("ID de l'utilisateur courant : " + usrId);
		if(this.myTasksItems.isEmpty()){
			List<Task> tasks = taskRemote.findTasksByUser(usrId);
			for(Task t:tasks){
				myTasksItems.add(new SelectItem(t.getTskId(), t.getTskLabel()));
			}
		}
		return myTasksItems;
	}
	
	public List<SelectItem> getCustomersItems() {
		if(this.customersItems.isEmpty()){
			List<Customer> customers = customerRemote.findActiveCustomers();
			for(Customer c:customers){
				customersItems.add(new SelectItem(c.getCusId(), c.getCusName()));
			}
		}
		return customersItems;
	}
	
	public List<SelectItem> getCdpItems() {
		if(this.cdpItems.isEmpty()){
			List<User> cdps = userRemote.findActiveCdps();
			for(User u:cdps){
				cdpItems.add(new SelectItem(u.getUsrId(), u.getUsrFirstname() + " " + u.getUsrLastname()));
			}
		}
		return cdpItems;
	}
	
	public List<SelectItem> getUsersItems() {
		if(this.usersItems.isEmpty()){
			List<User> users = userRemote.findActiveUsers();
			for(User u:users){
				usersItems.add(new SelectItem(u, u.getUsrFirstname() + " " + u.getUsrLastname()));
			}
		}
		return usersItems;
	}
	
	public List<SelectItem> getUsersItemsId() {
		if(this.usersItems.isEmpty()){
			List<User> users = userRemote.findActiveUsers();
			for(User u:users){
				usersItems.add(new SelectItem(u.getUsrId(), u.getUsrFirstname() + " " + u.getUsrLastname()));
			}
		}
		return usersItems;
	}
	
	public List<SelectItem> getProjectsItems() {
		if(this.projectsItems.isEmpty()){
			List<Project> projects = projectRemote.findActiveProjects();
			for(Project p:projects){
				projectsItems.add(new SelectItem(p.getPrjId(), p.getPrjLabel()));
			}
		}
		return projectsItems;
	}
	
	public List<SelectItem> getFuncItems() {
		if(this.funcItems.isEmpty()){
			List<Function> functions = functionRemote.findFunction();
			for (Function f:functions){
				funcItems.add(new SelectItem(f.getFctId(),f.getFctLabel()));
			}
		}
		return funcItems;
	}
	
	public List<SelectItem> getTstItems() {
		if(this.tstItems.isEmpty()){
			List<TaskType> tst = taskTypeRemote.findActiveTaskTypes();
			for (TaskType t:tst){
				tstItems.add(new SelectItem(t.getTstId(),t.getTstLabel()));
			}
		}
		return tstItems;
	}
	
	public List<SelectItem> getTssItems() {
		if(this.tssItems.isEmpty()){
			List<TaskStatus> tss = taskStatusRemote.findTaskStatus();
			for (TaskStatus t:tss){
				tssItems.add(new SelectItem(t.getTssId(),t.getTssLabel()));
			}
		}
		return tssItems;
	}
}
