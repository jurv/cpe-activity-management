package managedbeans;


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

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
}