package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the User database table.
 * 
 */
@Entity
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="usr_id")
	private int usrId;

	@Column(name="fct_id")
	private int fctId;

	@Column(name="usr_firstname")
	private String usrFirstname;

	@Column(name="usr_isadmin")
	private byte usrIsadmin;

	@Column(name="usr_isdeleted")
	private byte usrIsdeleted;

	@Column(name="usr_lastname")
	private String usrLastname;

	@Column(name="usr_login")
	private String usrLogin;

	@Column(name="usr_password")
	private String usrPassword;

	@Column(name="ust_id")
	private int ustId;

	public User() {
	}

	public int getUsrId() {
		return this.usrId;
	}

	public void setUsrId(int usrId) {
		this.usrId = usrId;
	}

	public int getFctId() {
		return this.fctId;
	}

	public void setFctId(int fctId) {
		this.fctId = fctId;
	}

	public String getUsrFirstname() {
		return this.usrFirstname;
	}

	public void setUsrFirstname(String usrFirstname) {
		this.usrFirstname = usrFirstname;
	}

	public byte getUsrIsadmin() {
		return this.usrIsadmin;
	}

	public void setUsrIsadmin(byte usrIsadmin) {
		this.usrIsadmin = usrIsadmin;
	}

	public byte getUsrIsdeleted() {
		return this.usrIsdeleted;
	}

	public void setUsrIsdeleted(byte usrIsdeleted) {
		this.usrIsdeleted = usrIsdeleted;
	}

	public String getUsrLastname() {
		return this.usrLastname;
	}

	public void setUsrLastname(String usrLastname) {
		this.usrLastname = usrLastname;
	}

	public String getUsrLogin() {
		return this.usrLogin;
	}

	public void setUsrLogin(String usrLogin) {
		this.usrLogin = usrLogin;
	}

	public String getUsrPassword() {
		return this.usrPassword;
	}

	public void setUsrPassword(String usrPassword) {
		this.usrPassword = usrPassword;
	}

	public int getUstId() {
		return this.ustId;
	}

	public void setUstId(int ustId) {
		this.ustId = ustId;
	}

}