package com.nhuocquy.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;

@Entity
public class Topic {
	@Id
	@GeneratedValue
	private long idTopic;
	@Type(type = "text")
	private String title;
	@OneToMany(mappedBy = "topic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Post> listPosts;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idgroup")
	private GroupTopic groupTopic;
	private boolean isCheck;
	public Topic() {
		listPosts = new ArrayList<Post>();
	}

	public Topic(String title, List<Post> listPosts) {
		super();
		this.title = title;
		this.listPosts = listPosts;
	}

	public long getIdTopic() {
		return idTopic;
	}

	public void setIdTopic(long idTopic) {
		this.idTopic = idTopic;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Post> getListPosts() {
		return listPosts;
	}

	public void setListPosts(List<Post> listPosts) {
		this.listPosts = listPosts;
	}

	public void addPost(Post p) {
		listPosts.add(p);
	}

	public GroupTopic getGroupTopic() {
		return groupTopic;
	}

	public void setGroupTopic(GroupTopic groupTopic) {
		this.groupTopic = groupTopic;
	}
	

	public boolean isCheck() {
		return isCheck;
	}

	public void setCheck(boolean isCheck) {
		this.isCheck = isCheck;
	}

	@Override
	public String toString() {
//		return "Topic [idTopic=" + idTopic + ", title=" + title + "]";
//	}
//
//	public String toStringListPostAndGroup() {
		return "Topic [idTopic=" + idTopic + ", title=" + title
				+ ", listPosts=" + listPosts + ", groupTopic=" + groupTopic
				+ "]";
	}

	public String toStringListPost() {
		return "Topic [idTopic=" + idTopic + ", title=" + title
				+ ", listPosts=" + listPosts + "]";
	}

}
