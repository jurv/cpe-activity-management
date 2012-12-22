package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the User2Project database table.
 * 
 */
@Entity
public class User2Project implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="utp_id")
	private int utpId;

	@Column(name="fct_id")
	private int fctId;

	@Column(name="prj_id")
	private int prjId;

	@Column(name="usr_id")
	private int usrId;

	@Column(name="utp_isdeleted")
	private byte utpIsdeleted;

	public User2Project() {
	}

	public int getUtpId() {
		return this.utpId;
	}

	public void setUtpId(int utpId) {
		this.utpId = utpId;
	}

	public int getFctId() {
		return this.fctId;
	}

	public void setFctId(int fctId) {
		this.fctId = fctId;
	}

	public int getPrjId() {
		return this.prjId;
	}

	public void setPrjId(int prjId) {
		this.prjId = prjId;
	}

	public int getUsrId() {
		return this.usrId;
	}

	public void setUsrId(int usrId) {
		this.usrId = usrId;
	}

	public byte getUtpIsdeleted() {
		return this.utpIsdeleted;
	}

	public void setUtpIsdeleted(byte utpIsdeleted) {
		this.utpIsdeleted = utpIsdeleted;
	}

}