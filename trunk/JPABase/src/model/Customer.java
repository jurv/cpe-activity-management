package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Customer database table.
 * 
 */
@Entity
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="cus_id")
	private int cusId;

	@Column(name="cus_isdeleted")
	private byte cusIsdeleted;

	@Column(name="cus_name")
	private String cusName;

	public Customer() {
	}

	public int getCusId() {
		return this.cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	public byte getCusIsdeleted() {
		return this.cusIsdeleted;
	}

	public void setCusIsdeleted(byte cusIsdeleted) {
		this.cusIsdeleted = cusIsdeleted;
	}

	public String getCusName() {
		return this.cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

}