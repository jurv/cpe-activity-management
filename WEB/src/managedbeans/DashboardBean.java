package managedbeans;


import java.util.HashMap;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import model.User;


import com.cpeeterprise.BeanUserRemote;

@ManagedBean
@SessionScoped
public class DashboardBean {
	
	@EJB
	public BeanUserRemote userRemote;
	private User currentUser;
	private HashMap <String, Integer> tasksStatus = new HashMap<String, Integer>(); 
	
	public void initView () {
		
		// On remplit les décomptes de tâches pour l'utilisateur courant
		
		
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public HashMap <String, Integer> getTasksStatus() {
		return tasksStatus;
	}

	public void setTasksStatus(HashMap <String, Integer> tasksStatus) {
		this.tasksStatus = tasksStatus;
	}
}