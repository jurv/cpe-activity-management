package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the Message database table.
 * 
 */
@Entity
public class Message implements Serializable , Comparable<Message> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="msg_id")
	private int msgId;

	@Lob
	@Column(name="msg_content")
	private String msgContent;

	@Column(name="msg_date")
	private Timestamp msgDate;

	@Column(name="msg_isdeleted")
	private byte msgIsdeleted;

	@Column(name="msg_isread")
	private byte msgIsread;

	@Column(name="msg_subject")
	private String msgSubject;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="usr_sender_id")
	private User sender;
	
	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="usr_receiver_id")
	private User receiver;
	
	//bi-directional many-to-one association to Project
	@ManyToOne
	@JoinColumn(name="prj_id")
	private Project project;

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

	public Timestamp getMsgDate() {
		return this.msgDate;
	}

	public void setMsgDate(Timestamp msgDate) {
		this.msgDate = msgDate;
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

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	@Override
	public int compareTo(Message o) {
		return Integer.compare(getMsgId(), o.getMsgId());
	}
	
	@Override
	public boolean equals (Object o) {
		return (this.msgId == ((Message)o).getMsgId());
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
}