package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the Project database table.
 * 
 */
@Entity
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="prj_id")
	private int prjId;

	@Column(name="cus_id")
	private int cusId;

	@Lob
	@Column(name="prj_comment")
	private String prjComment;

	@Temporal(TemporalType.DATE)
	@Column(name="prj_date_created")
	private Date prjDateCreated;

	@Temporal(TemporalType.DATE)
	@Column(name="prj_date_finished")
	private Date prjDateFinished;

	@Column(name="prj_isdeleted")
	private byte prjIsdeleted;

	@Column(name="prj_label")
	private String prjLabel;

	@Column(name="prj_nb_days")
	private int prjNbDays;

	//bi-directional many-to-one association to Message
	@OneToMany(mappedBy="project")
	private List<Message> messages;

	public Project() {
	}

	public int getPrjId() {
		return this.prjId;
	}

	public void setPrjId(int prjId) {
		this.prjId = prjId;
	}

	public int getCusId() {
		return this.cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	public String getPrjComment() {
		return this.prjComment;
	}

	public void setPrjComment(String prjComment) {
		this.prjComment = prjComment;
	}

	public Date getPrjDateCreated() {
		return this.prjDateCreated;
	}

	public void setPrjDateCreated(Date prjDateCreated) {
		this.prjDateCreated = prjDateCreated;
	}

	public Date getPrjDateFinished() {
		return this.prjDateFinished;
	}

	public void setPrjDateFinished(Date prjDateFinished) {
		this.prjDateFinished = prjDateFinished;
	}

	public byte getPrjIsdeleted() {
		return this.prjIsdeleted;
	}

	public void setPrjIsdeleted(byte prjIsdeleted) {
		this.prjIsdeleted = prjIsdeleted;
	}

	public String getPrjLabel() {
		return this.prjLabel;
	}

	public void setPrjLabel(String prjLabel) {
		this.prjLabel = prjLabel;
	}

	public int getPrjNbDays() {
		return this.prjNbDays;
	}

	public void setPrjNbDays(int prjNbDays) {
		this.prjNbDays = prjNbDays;
	}

	public List<Message> getMessages() {
		return this.messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

}