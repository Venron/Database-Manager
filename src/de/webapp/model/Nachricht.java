package de.webapp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "nachricht")

@NamedQueries({ 
		@NamedQuery(name="Nachricht.findByEmpfaenger", query="SELECT n FROM Nachricht n WHERE n.empfaenger = :email ORDER BY n.msg_id DESC"),
		@NamedQuery(name="Nachricht.findBySender", query="SELECT n FROM Nachricht n WHERE n.sender = :email ORDER BY n.msg_id DESC"),
		})
public class Nachricht implements Serializable {

	private static final long serialVersionUID = 1L;

	private int msg_id;
	private String sender;
	private String empfaenger;
	private String text;

	public Nachricht() {

	}

	@Id
	@SequenceGenerator(name = "nachrichtSeq", sequenceName = "APP_MSG_SEQ", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nachrichtSeq")
	public int getMsg_id() {
		return msg_id;
	}

	public void setMsg_id(int msg_id) {
		this.msg_id = msg_id;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getEmpfaenger() {
		return empfaenger;
	}

	public void setEmpfaenger(String empfaenger) {
		this.empfaenger = empfaenger;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Nachricht [msg_id=" + msg_id + ", sender=" + sender + ", empfaenger=" + empfaenger + ", text=" + text
				+ "]";
	}

}
