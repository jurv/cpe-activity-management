package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the Connection database table.
 * 
 */
@Entity
public class Connection implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="con_id")
	private int conId;

	@Temporal(TemporalType.DATE)
	@Column(name="con_date_begin")
	private Date conDateBegin;

	@Temporal(TemporalType.DATE)
	@Column(name="con_date_end")
	private Date conDateEnd;

	@Column(name="con_isdeleted")
	private byte conIsdeleted;

	@Column(name="usr_id")
	private int usrId;

	public Connection() {
	}

	public int getConId() {
		return this.conId;
	}

	public void setConId(int conId) {
		this.conId = conId;
	}

	public Date getConDateBegin() {
		return this.conDateBegin;
	}

	public void setConDateBegin(Date conDateBegin) {
		this.conDateBegin = conDateBegin;
	}

	public Date getConDateEnd() {
		return this.conDateEnd;
	}

	public void setConDateEnd(Date conDateEnd) {
		this.conDateEnd = conDateEnd;
	}

	public byte getConIsdeleted() {
		return this.conIsdeleted;
	}

	public void setConIsdeleted(byte conIsdeleted) {
		this.conIsdeleted = conIsdeleted;
	}

	public int getUsrId() {
		return this.usrId;
	}

	public void setUsrId(int usrId) {
		this.usrId = usrId;
	}

}