package managedbeans;


import java.util.ArrayList;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import model.User;

import com.cpeeterprise.BeanUserRemote;

@ManagedBean
@ApplicationScoped
public class UsersBean {
	
	@EJB
	public BeanUserRemote userRemote;
		
	public boolean deleteUser() {
		int usrId = Integer.parseInt((String)FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("userIdDel"));
		User user = userRemote.findUser(usrId);
		userRemote.delete(user);
		return true;
	}
}