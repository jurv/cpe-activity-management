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
	public User current;

	public boolean connectUser() {
		return userRemote.connectUser(current);
	}
}