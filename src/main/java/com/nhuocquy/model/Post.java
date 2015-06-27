package com.nhuocquy.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Type;

@Entity
public class Post {
	@Id
	@GeneratedValue
	private long idPost;
	@OneToOne(cascade = CascadeType.ALL)
	private Friend poster;
	@Type(type = "timestamp")
	private Date datePost;
	@Type(type = "text")
	private String context;
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "post_image", joinColumns = @JoinColumn(name = "idpost"))
	@Column(name = "image")
	private List<String> images;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idtopic")
	private Topic topic;
	private int clike;
	private int cdislike;
	public Post() {
		images = new ArrayList<String>();
	}

	public Post(Friend poster, Date datePost, String context,
			List<String> images) {
		super();
		this.poster = poster;
		this.datePost = datePost;
		this.context = context;
		this.images = images;
	}

	public long getIdPost() {
		return idPost;
	}

	public void setIdPost(long idPost) {
		this.idPost = idPost;
	}

	public Friend getPoster() {
		return poster;
	}

	public void setPoster(Friend poster) {
		this.poster = poster;
	}

	public Date getDatePost() {
		return datePost;
	}

	public void setDatePost(Date datePost) {
		this.datePost = datePost;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	
	
	public int getClike() {
		return clike;
	}

	public void setClike(int clike) {
		this.clike = clike;
	}

	public int getCdislike() {
		return cdislike;
	}

	public void setCdislike(int cdislike) {
		this.cdislike = cdislike;
	}

	public void like(){
		clike++;
	}
	public void dislike(){
		cdislike++;
	}
	@Override
	public String toString() {
		return "Post [idPost=" + idPost + ", poster=" + poster + ", datePost="
				+ datePost + ", context=" + context + ", images=" + images
				+ "]";
	}

	public String toStringTopic() {
		return "Post [idPost=" + idPost + ", poster=" + poster + ", datePost="
				+ datePost + ", context=" + context + ", images=" + images
				+ ", topic=" + topic + "]";
	}

}
