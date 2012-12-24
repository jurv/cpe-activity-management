package managedbeans;


import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.User;


import com.cpeeterprise.BeanUserRemote;

@ManagedBean
@SessionScoped
public class LoginFormBean {
	
	@EJB
	public BeanUserRemote userRemote;
	private User current = new User();

	public String connectUser() {
		return userRemote.connectUser(getCurrent().getUsrLogin(), getCurrent().getUsrPassword());
	}

	public User getCurrent() {
		return current;
	}

	public void setCurrent(User current) {
		this.current = current;
	}
}