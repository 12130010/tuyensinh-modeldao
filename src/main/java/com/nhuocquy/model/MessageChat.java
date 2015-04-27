package com.nhuocquy.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Type;
@Entity
@Table(name="message")
@Immutable
public class MessageChat implements Serializable {
	@Transient
	private static final long serialVersionUID = 4855247675318069736L;
	@Id
	@GeneratedValue
	private long idMes;
	private long idSender;
	private String fromName;
	private String text;
	@Type(type="timestamp")
	private Date date;
	@Transient
	private long idConversation;

	public MessageChat() {
	}

	public MessageChat(long idMes, long idSender, String fromName, String text,
			Date date) {
		super();
		this.idMes = idMes;
		this.idSender = idSender;
		this.fromName = fromName;
		this.text = text;
		this.date = date;
	}

	public long getIdMes() {
		return idMes;
	}

	public void setIdMes(long idMes) {
		this.idMes = idMes;
	}

	public long getIdSender() {
		return idSender;
	}

	public void setIdSender(long idSender) {
		this.idSender = idSender;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public long getIdConversation() {
		return idConversation;
	}

	public void setIdConversation(long idConversation) {
		this.idConversation = idConversation;
	}
	
}
