package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TaskStatus database table.
 * 
 */
@Entity
public class TaskStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="tss_id")
	private int tssId;

	@Column(name="tss_isdeleted")
	private byte tssIsdeleted;

	@Column(name="tss_label")
	private String tssLabel;

	public TaskStatus() {
	}

	public int getTssId() {
		return this.tssId;
	}

	public void setTssId(int tssId) {
		this.tssId = tssId;
	}

	public byte getTssIsdeleted() {
		return this.tssIsdeleted;
	}

	public void setTssIsdeleted(byte tssIsdeleted) {
		this.tssIsdeleted = tssIsdeleted;
	}

	public String getTssLabel() {
		return this.tssLabel;
	}

	public void setTssLabel(String tssLabel) {
		this.tssLabel = tssLabel;
	}

}