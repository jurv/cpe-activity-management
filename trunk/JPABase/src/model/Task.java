package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the Task database table.
 * 
 */
@Entity
public class Task implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="tsk_id")
	private int tskId;

	@Column(name="prj_id")
	private int prjId;

	@Temporal(TemporalType.DATE)
	@Column(name="tsk_date_assigned")
	private Date tskDateAssigned;

	@Temporal(TemporalType.DATE)
	@Column(name="tsk_date_begun")
	private Date tskDateBegun;

	@Temporal(TemporalType.DATE)
	@Column(name="tsk_date_deadline")
	private Date tskDateDeadline;

	@Temporal(TemporalType.DATE)
	@Column(name="tsk_date_finished")
	private Date tskDateFinished;

	@Lob
	@Column(name="tsk_description")
	private String tskDescription;

	@Column(name="tsk_duration")
	private int tskDuration;

	@Column(name="tsk_duration_done")
	private int tskDurationDone;

	@Column(name="tsk_isdeleted")
	private byte tskIsdeleted;

	@Column(name="tsk_label")
	private String tskLabel;

	@Column(name="tsk_level")
	private int tskLevel;

	@Column(name="tsk_parent_id")
	private int tskParentId;

	@Column(name="tst_id")
	private int tstId;

	@Column(name="usr_assignedby_id")
	private int usrAssignedbyId;

	@Column(name="usr_assignedto_id")
	private int usrAssignedtoId;

	public Task() {
	}

	public int getTskId() {
		return this.tskId;
	}

	public void setTskId(int tskId) {
		this.tskId = tskId;
	}

	public int getPrjId() {
		return this.prjId;
	}

	public void setPrjId(int prjId) {
		this.prjId = prjId;
	}

	public Date getTskDateAssigned() {
		return this.tskDateAssigned;
	}

	public void setTskDateAssigned(Date tskDateAssigned) {
		this.tskDateAssigned = tskDateAssigned;
	}

	public Date getTskDateBegun() {
		return this.tskDateBegun;
	}

	public void setTskDateBegun(Date tskDateBegun) {
		this.tskDateBegun = tskDateBegun;
	}

	public Date getTskDateDeadline() {
		return this.tskDateDeadline;
	}

	public void setTskDateDeadline(Date tskDateDeadline) {
		this.tskDateDeadline = tskDateDeadline;
	}

	public Date getTskDateFinished() {
		return this.tskDateFinished;
	}

	public void setTskDateFinished(Date tskDateFinished) {
		this.tskDateFinished = tskDateFinished;
	}

	public String getTskDescription() {
		return this.tskDescription;
	}

	public void setTskDescription(String tskDescription) {
		this.tskDescription = tskDescription;
	}

	public int getTskDuration() {
		return this.tskDuration;
	}

	public void setTskDuration(int tskDuration) {
		this.tskDuration = tskDuration;
	}

	public int getTskDurationDone() {
		return this.tskDurationDone;
	}

	public void setTskDurationDone(int tskDurationDone) {
		this.tskDurationDone = tskDurationDone;
	}

	public byte getTskIsdeleted() {
		return this.tskIsdeleted;
	}

	public void setTskIsdeleted(byte tskIsdeleted) {
		this.tskIsdeleted = tskIsdeleted;
	}

	public String getTskLabel() {
		return this.tskLabel;
	}

	public void setTskLabel(String tskLabel) {
		this.tskLabel = tskLabel;
	}

	public int getTskLevel() {
		return this.tskLevel;
	}

	public void setTskLevel(int tskLevel) {
		this.tskLevel = tskLevel;
	}

	public int getTskParentId() {
		return this.tskParentId;
	}

	public void setTskParentId(int tskParentId) {
		this.tskParentId = tskParentId;
	}

	public int getTstId() {
		return this.tstId;
	}

	public void setTstId(int tstId) {
		this.tstId = tstId;
	}

	public int getUsrAssignedbyId() {
		return this.usrAssignedbyId;
	}

	public void setUsrAssignedbyId(int usrAssignedbyId) {
		this.usrAssignedbyId = usrAssignedbyId;
	}

	public int getUsrAssignedtoId() {
		return this.usrAssignedtoId;
	}

	public void setUsrAssignedtoId(int usrAssignedtoId) {
		this.usrAssignedtoId = usrAssignedtoId;
	}

}