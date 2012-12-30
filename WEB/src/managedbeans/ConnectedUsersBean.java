package managedbeans;


import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.User;


import com.cpeeterprise.BeanUserRemote;

@ManagedBean
@SessionScoped
public class ConnectedUsersBean {
	
	@EJB
	public BeanUserRemote userRemote;
	private User currentUser;
	private List<User> connectedUsers;
	
	public String checkUsersConnected() {
		setConnectedUsers(userRemote.getConnectedUsers());
		String ret = "";
		for( User cur : connectedUsers ) {
			ret += cur.getUsrFirstname() + " " + cur.getUsrLastname() + "<br/>";
		}
		
		return ret;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public List<User> getConnectedUsers() {
		return connectedUsers;
	}

	public void setConnectedUsers(List<User> connectedUsers) {
		this.connectedUsers = connectedUsers;
	}

}