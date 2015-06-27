package com.nhuocquy.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class GroupTopic {
	@Id
	@GeneratedValue
	private long idGroupTopic;
	@OneToMany(mappedBy = "groupTopic", fetch = FetchType.LAZY)
//	@JoinTable(name = "grouptopic_topic", joinColumns = @JoinColumn(name = "idgroup"), inverseJoinColumns = @JoinColumn(name = "idtopic"), uniqueConstraints = @UniqueConstraint (columnNames = {"idgroup","idtopic"}))
	private List<Topic> lisTopics;
	private String groupName;
	public GroupTopic() {
		lisTopics = new ArrayList<Topic>();
	}
	
	public GroupTopic(List<Topic> lisTopics, String groupName) {
		super();
		this.lisTopics = lisTopics;
		this.groupName = groupName;
	}

	public long getIdGroupTopic() {
		return idGroupTopic;
	}
	public void setIdGroupTopic(long idGroupTopic) {
		this.idGroupTopic = idGroupTopic;
	}
	public List<Topic> getLisTopics() {
		return lisTopics;
	}
	public void setLisTopics(List<Topic> lisTopics) {
		this.lisTopics = lisTopics;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public void addTopic(Topic top){
		lisTopics.add(top);
	}

	
	public String toStringListTopic() {
		return "GroupTopic [idGroupTopic=" + idGroupTopic + ", lisTopics="
				+ lisTopics + ", groupName=" + groupName + "]";
	}

	public String toString() {
		return "GroupTopic [idGroupTopic=" + idGroupTopic +  ", groupName=" + groupName + "]";
	}
	
}
