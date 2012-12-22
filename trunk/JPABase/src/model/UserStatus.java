package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the UserStatus database table.
 * 
 */
@Entity
public class UserStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ust_id")
	private int ustId;

	@Column(name="ust_isdeleted")
	private byte ustIsdeleted;

	@Column(name="ust_label")
	private String ustLabel;

	public UserStatus() {
	}

	public int getUstId() {
		return this.ustId;
	}

	public void setUstId(int ustId) {
		this.ustId = ustId;
	}

	public byte getUstIsdeleted() {
		return this.ustIsdeleted;
	}

	public void setUstIsdeleted(byte ustIsdeleted) {
		this.ustIsdeleted = ustIsdeleted;
	}

	public String getUstLabel() {
		return this.ustLabel;
	}

	public void setUstLabel(String ustLabel) {
		this.ustLabel = ustLabel;
	}

}