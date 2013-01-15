package model;

import java.io.Serializable;
import javax.persistence.*;


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

	@Column(name="usr_id")
	private int usrId;

	@Column(name="wrk_comment")
	private int wrkComment;

	@Column(name="wrk_date")
	private int wrkDate;

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

	public int getUsrId() {
		return this.usrId;
	}

	public void setUsrId(int usrId) {
		this.usrId = usrId;
	}

	public int getWrkComment() {
		return this.wrkComment;
	}

	public void setWrkComment(int wrkComment) {
		this.wrkComment = wrkComment;
	}

	public int getWrkDate() {
		return this.wrkDate;
	}

	public void setWrkDate(int wrkDate) {
		this.wrkDate = wrkDate;
	}

	public int getWrkDuration() {
		return this.wrkDuration;
	}

	public void setWrkDuration(int wrkDuration) {
		this.wrkDuration = wrkDuration;
	}

}