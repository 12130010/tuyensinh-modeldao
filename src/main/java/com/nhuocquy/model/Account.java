package com.nhuocquy.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "acount")
public class Account implements Serializable {
	@Transient
	private static final long serialVersionUID = 2569510220972262364L;
	@Id
	@GeneratedValue
	private long idAcc;
	private String name;
	private String username;
	private String password;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "acc_fri", joinColumns = @JoinColumn(name = "idacc"), inverseJoinColumns = @JoinColumn(name = "idfri"),indexes={})
	private List<Friend> listFrs;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "acc_makefri", joinColumns = @JoinColumn(name = "idacc"), inverseJoinColumns = @JoinColumn(name = "idfri"))
	private List<Friend> listMakeFrs;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "acc_con", joinColumns = @JoinColumn(name = "idacc"), inverseJoinColumns = @JoinColumn(name = "idcon"))
	private List<Conversation> conversations;

	private String address;
	private Date birthday;
	private String avatar;
	public Account() {
		listFrs = new ArrayList<Friend>();
		listMakeFrs = new ArrayList<Friend>();
		conversations = new ArrayList<Conversation>();
	}

	public Account(long idAcc, String name, List<Friend> listFrs) {
		super();
		this.idAcc = idAcc;
		this.name = name;
		this.listFrs = listFrs;
	}

	public long getIdAcc() {
		return idAcc;
	}

	public void setIdAcc(long idAcc) {
		this.idAcc = idAcc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Friend> getListFrs() {
		return listFrs;
	}

	public void setListFrs(List<Friend> listFrs) {
		this.listFrs = listFrs;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Conversation> getConversations() {
		return conversations;
	}

	public void setConversations(List<Conversation> conversations) {
		this.conversations = conversations;
	}

	public List<Friend> getListMakeFrs() {
		return listMakeFrs;
	}

	public void setListMakeFrs(List<Friend> listMakeFrs) {
		this.listMakeFrs = listMakeFrs;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Friend retrieveAccountAsFriend() {
		return new Friend(idAcc, name);
	}

	@Override
	public String toString() {
		return "Account [idAcc=" + idAcc + ", name=" + name + ", username="
				+ username + ", password=" + password + ", listFrs=" + listFrs
				+ ", listMakeFrs=" + listMakeFrs + ", conversations="
				+ conversations + ", address=" + address + ", birthday="
				+ birthday + "]";
	}

	public void addFriend(Friend f) {
		listFrs.add(f);
	}

	public void addMakeFriend(Friend f) {
		listMakeFrs.add(f);
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

}
