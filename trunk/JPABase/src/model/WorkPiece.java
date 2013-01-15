package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the WorkPiece database table.
 * 
 */
@Entity
public class WorkPiece implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="wrk_id")
	private int wrkId;

	@Column(name="tsk_id")
	private int tskId;

	@Column(name="tss_id")
	private int tssId;

	@Column(name="usr_id")
	private int usrId;

	@Lob
	@Column(name="wrk_comment")
	private String wrkComment;

	@Temporal(TemporalType.DATE)
	@Column(name="wrk_date")
	private Date wrkDate;

	@Column(name="wrk_duration")
	private int wrkDuration;

	public WorkPiece() {
	}

	public int getWrkId() {
		return this.wrkId;
	}

	public void setWrkId(int wrkId) {
		this.wrkId = wrkId;
	}

	public int getTskId() {
		return this.tskId;
	}

	public void setTskId(int tskId) {
		this.tskId = tskId;
	}

	public int getTssId() {
		return this.tssId;
	}

	public void setTssId(int tssId) {
		this.tssId = tssId;
	}

	public int getUsrId() {
		return this.usrId;
	}

	public void setUsrId(int usrId) {
		this.usrId = usrId;
	}

	public String getWrkComment() {
		return this.wrkComment;
	}

	public void setWrkComment(String wrkComment) {
		this.wrkComment = wrkComment;
	}

	public Date getWrkDate() {
		return this.wrkDate;
	}

	public void setWrkDate(Date wrkDate) {
		this.wrkDate = wrkDate;
	}

	public int getWrkDuration() {
		return this.wrkDuration;
	}

	public void setWrkDuration(int wrkDuration) {
		this.wrkDuration = wrkDuration;
	}

}