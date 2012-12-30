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
public class LoginFormBean {
	
	@EJB
	public BeanUserRemote userRemote;
	private User current = new User();

	public String connectUser() {
		String ret = "Unconnected";
		Integer usrId = userRemote.connectUser(getCurrent().getUsrLogin(), getCurrent().getUsrPassword());
		
		if(usrId > 0) {
			current = userRemote.findUser(usrId);
			ret = "Connected";
			
			// Stockage de l'identifiant utilisateur dans la session.
			FacesContext fc = FacesContext.getCurrentInstance();
			if(fc != null && fc.getExternalContext() != null) {
				HttpSession sess = (HttpSession)fc.getExternalContext().getSession(false);
				sess.setAttribute("usrId", usrId);
			}
		}
		
		return ret;
	}		

	public User getCurrent() {
		return current;
	}

	public void setCurrent(User current) {
		this.current = current;
	}
}