package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Message database table.
 * 
 */
@Entity
public class Message implements Serializable, Comparable<Message>{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="msg_id")
	private int msgId;

	@Lob
	@Column(name="msg_content")
	private String msgContent;

	@Column(name="msg_isdeleted")
	private byte msgIsdeleted;

	@Column(name="msg_isread")
	private byte msgIsread;

	@Column(name="msg_subject")
	private String msgSubject;

	@Column(name="prj_id")
	private int prjId;

	@Column(name="usr_receiver_id")
	private int usrReceiverId;

	@Column(name="usr_sender_id")
	private int usrSenderId;

	public Message() {
	}

	public int getMsgId() {
		return this.msgId;
	}

	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}

	public String getMsgContent() {
		return this.msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public byte getMsgIsdeleted() {
		return this.msgIsdeleted;
	}

	public void setMsgIsdeleted(byte msgIsdeleted) {
		this.msgIsdeleted = msgIsdeleted;
	}

	public byte getMsgIsread() {
		return this.msgIsread;
	}

	public void setMsgIsread(byte msgIsread) {
		this.msgIsread = msgIsread;
	}

	public String getMsgSubject() {
		return this.msgSubject;
	}

	public void setMsgSubject(String msgSubject) {
		this.msgSubject = msgSubject;
	}

	public int getPrjId() {
		return this.prjId;
	}

	public void setPrjId(int prjId) {
		this.prjId = prjId;
	}

	public int getUsrReceiverId() {
		return this.usrReceiverId;
	}

	public void setUsrReceiverId(int usrReceiverId) {
		this.usrReceiverId = usrReceiverId;
	}

	public int getUsrSenderId() {
		return this.usrSenderId;
	}

	public void setUsrSenderId(int usrSenderId) {
		this.usrSenderId = usrSenderId;
	}

	@Override
	public int compareTo(Message o) {
		return Integer.compare(getMsgId(), o.getMsgId());
	}
	
	@Override
	public boolean equals (Object o) {
		return (this.msgId == ((Message)o).getMsgId());
	}
}