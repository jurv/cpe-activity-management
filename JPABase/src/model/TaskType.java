package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TaskType database table.
 * 
 */
@Entity
public class TaskType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="tst_id")
	private int tstId;

	@Column(name="tst_isdeleted")
	private byte tstIsdeleted;

	@Column(name="tst_label")
	private String tstLabel;

	public TaskType() {
	}

	public int getTstId() {
		return this.tstId;
	}

	public void setTstId(int tstId) {
		this.tstId = tstId;
	}

	public byte getTstIsdeleted() {
		return this.tstIsdeleted;
	}

	public void setTstIsdeleted(byte tstIsdeleted) {
		this.tstIsdeleted = tstIsdeleted;
	}

	public String getTstLabel() {
		return this.tstLabel;
	}

	public void setTstLabel(String tstLabel) {
		this.tstLabel = tstLabel;
	}

}