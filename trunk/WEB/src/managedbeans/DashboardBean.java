package managedbeans;


import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.User;


import com.cpeeterprise.BeanUserRemote;

@ManagedBean
@SessionScoped
public class DashboardBean {
	
	@EJB
	public BeanUserRemote userRemote;
	private User currentUser = new User();

	public User getCurrent() {
		return currentUser;
	}

	public void setCurrent(User currentUser) {
		this.currentUser = currentUser;
	}
}