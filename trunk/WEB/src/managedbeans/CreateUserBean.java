package managedbeans;


import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.User;
import com.cpeeterprise.BeanUserRemote;

@ManagedBean
@SessionScoped
public class CreateUserBean {
	
	@EJB
	public BeanUserRemote userRemote;
	
	private String userLastName = "";
	private String userFirstName = "";
	private String userLogin = "";
	private String userPassword = "";
	private int userFctId;
	
	public String updateUser()
	{
		String nextPage="Users";
		User user = new User();
		user.setUsrLastname(this.getUserLastName());
		user.setUsrFirstname(this.getUserFirstName());
		user.setUsrLogin(this.getUserLogin());
		user.setUsrPassword(getUserPassword());
		user.setFctId(getUserFctId());
		userRemote.update(user);
		return nextPage;
	}

	public String getUserLastName() {
		return userLastName;
	}


	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}


	public String getUserFirstName() {
		return userFirstName;
	}


	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}


	public String getUserLogin() {
		return userLogin;
	}


	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}


	public String getUserPassword() {
		return userPassword;
	}


	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	public int getUserFctId() {
		return userFctId;
	}

	public void setUserFctId(int userFctId) {
		this.userFctId = userFctId;
	}

}
