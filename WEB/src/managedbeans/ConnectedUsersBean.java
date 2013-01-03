package managedbeans;


import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.event.ActionEvent;

import model.User;


import com.cpeeterprise.BeanUserRemote;

@ManagedBean
@SessionScoped
public class ConnectedUsersBean {
	
	@EJB
	public BeanUserRemote userRemote;
	private User currentUser;
	private List<User> connectedUsers;
	private String connectedUsersString = "";
	private String connectedUsersWindowsString = "";

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

	public String getConnectedUsersString() {
		
		setConnectedUsers(userRemote.getConnectedUsers());
		String connectedUsersString = "";
		for( User cur : connectedUsers ) {
			connectedUsersString += "<div class='chat-element' pid='" + cur.getUsrId() + "' pfname='" + cur.getUsrFirstname() + "' plname='" + cur.getUsrLastname() + "' >" + cur.getUsrFirstname() + " " + cur.getUsrLastname() + "</div>" + "<br/>";
		}
		
		return connectedUsersString;
	}

	public void setConnectedUsersString(String connectedUsersString) {
		this.connectedUsersString = connectedUsersString;
	}
	
	public void sendMessage (ActionEvent evt) {

		String test = "";
	}

	public String getConnectedUsersWindowsString() {
		
		String connectedUsersWindowsString = "";
		for( User cur : connectedUsers ) {
			
			connectedUsersWindowsString += "<div class='chat-window' pid='" + cur.getUsrId() + "'>" +
										   "	<div class='chat-window-header'> " + cur.getUsrFirstname() + " " + cur.getUsrLastname() + 
										   "	</div>" +
										   "	<div id='message-content-" + cur.getUsrId() + "' class='chat-window-content'>" +
										   "    	<div class='chat-window-content-controls'> " +
										   "			<h:form id='form-" + cur.getUsrId() + "' name='form-" + cur.getUsrId() + "'> " +	
		   					   			   "	        	<h:outputText id='messages-" + cur.getUsrId() + "'> </h:outputText> " +
		   					   			   "				<h:commandButton value='Go' actionListener='#{ConnectedUsersBean.sendMessage}'> </h:commandButton> " +
		   					   			   "                <h:inputText id='input-text-" + cur.getUsrId() + "'></h:outputText> " +
		   					   			   "            </h:form> " +
										   "        </div>" +
										   "    </div>" +
										   "</div>";
		}
		return connectedUsersWindowsString;
	}

	public void setConnectedUsersWindowsString(String connectedUsersWindowsString) {
		this.connectedUsersWindowsString = connectedUsersWindowsString;
	}

}