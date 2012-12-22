package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Function database table.
 * 
 */
@Entity
public class Function implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="fct_id")
	private int fctId;

	@Column(name="fct_isdeleted")
	private byte fctIsdeleted;

	@Column(name="fct_label")
	private String fctLabel;

	@Column(name="fct_level")
	private int fctLevel;

	public Function() {
	}

	public int getFctId() {
		return this.fctId;
	}

	public void setFctId(int fctId) {
		this.fctId = fctId;
	}

	public byte getFctIsdeleted() {
		return this.fctIsdeleted;
	}

	public void setFctIsdeleted(byte fctIsdeleted) {
		this.fctIsdeleted = fctIsdeleted;
	}

	public String getFctLabel() {
		return this.fctLabel;
	}

	public void setFctLabel(String fctLabel) {
		this.fctLabel = fctLabel;
	}

	public int getFctLevel() {
		return this.fctLevel;
	}

	public void setFctLevel(int fctLevel) {
		this.fctLevel = fctLevel;
	}

}