package com.nhuocquy.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "friend")
public class Friend implements Serializable {
	@Transient
	private static final long serialVersionUID = 4192572816752766041L;
	@Id
	@GeneratedValue
	private long idFriend;
	private String name;
	private String avatar;
	public Friend() {
	}

	public Friend(long idFriend, String name) {
		super();
		this.idFriend = idFriend;
		this.name = name;
	}

	public long getIdFriend() {
		return idFriend;
	}

	public void setIdFriend(long idFriend) {
		this.idFriend = idFriend;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	@Override
	public boolean equals(Object obj) {
		if (obj==null ||  !(obj instanceof Friend))
		return false;
		return ((Friend) obj).idFriend == idFriend;
	}

	@Override
	public String toString() {
		return "Friend [idFriend=" + idFriend + ", name=" + name + "]";
	}

	
	
}
