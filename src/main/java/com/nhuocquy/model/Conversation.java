package com.nhuocquy.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "conversation")
public class Conversation implements Serializable{
	@Transient
	private static final long serialVersionUID = 8570601163700722869L;
	@Transient
	private boolean readed;
	@Id
	@GeneratedValue
	private long idCon;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "con_fri",joinColumns=@JoinColumn(name="idcon"),
	inverseJoinColumns=@JoinColumn(name="idfri"))
	private List<Friend> friends;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "con_mes", joinColumns=@JoinColumn(name="idcon"),
	inverseJoinColumns=@JoinColumn(name="idmes"))
	private List<MessageChat> listMes;
	
	public Conversation() {
		friends = new ArrayList<Friend>();
		listMes = new ArrayList<MessageChat>();
	}

	public Conversation(long idCon, List<Friend> friends, List<MessageChat> listMes) {
		super();
		this.idCon = idCon;
		this.friends = friends;
		this.listMes = listMes;
	}

	public long getIdCon() {
		return idCon;
	}

	public void setIdCon(long idCon) {
		this.idCon = idCon;
	}

	public List<Friend> getFriends() {
		return friends;
	}

	public void setFriends(List<Friend> friends) {
		this.friends = friends;
	}

	public List<MessageChat> getListMes() {
		return listMes;
	}

	public void setListMes(List<MessageChat> listMes) {
		this.listMes = listMes;
	}
	public String selectNames(){
		StringBuilder sb = new StringBuilder();
		for (Friend f : friends) {
			sb.append(f.getName());
			sb.append(", ");
		}
		sb.delete(sb.length() -1, sb.length());
		
		return sb.toString();
	}
	public boolean containIDFri(long idFri){
		return friends.contains(new Friend(idFri, ""));
	}
	public boolean addMessageChat(MessageChat messageChat){
		return listMes.add(messageChat);
	}

	@Override
	public String toString() {
		return "Conversation [readed=" + readed + ", idCon=" + idCon
				+ ", friends=" + friends + ", listMes=" + listMes + "]";
	}

	public boolean isReaded() {
		return readed;
	}

	public void setReaded(boolean readed) {
		this.readed = readed;
	}
	
}
