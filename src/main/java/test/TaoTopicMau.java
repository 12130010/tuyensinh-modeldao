package test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nhuocquy.dao.DAOGroupTopic;
import com.nhuocquy.dao.DAOPost;
import com.nhuocquy.dao.exception.DAOException;
import com.nhuocquy.model.Friend;
import com.nhuocquy.model.GroupTopic;
import com.nhuocquy.model.Post;
import com.nhuocquy.model.Topic;

public class TaoTopicMau {
	public static void main(String[] args) throws DAOException {
		DAOGroupTopic daoGroupTopic = new DAOGroupTopic();
		DAOPost daoPost = new DAOPost();
		List<GroupTopic> liGroupTopics = new ArrayList<GroupTopic>();
		for (int i = 0; i < 5; i++) {
			GroupTopic groupTopic = new GroupTopic();
			groupTopic.setGroupName("Khoa " + i);
			List<Topic> lTopics = new ArrayList<Topic>();
			for (int j = 0; j < 10; j++) {
				Topic topic = new Topic();
				topic.setGroupTopic(groupTopic);
				topic.setTitle("Topic " + j);
				List<Post> lPosts = new ArrayList<Post>();
				for (int k = 0; k < 10; k++) {
					Post post = new Post();
					post.setContext("context " + k);
					post.setDatePost(new Date());
					post.setImages(new ArrayList<String>());
					post.setPoster(new Friend(1, "Nhuoc Quy"));
					post.setTopic(topic);
					lPosts.add(post);
					daoPost.save2(post);
				}
				topic.setListPosts(lPosts);
				lTopics.add(topic);
			}
			groupTopic.setLisTopics(lTopics);
//			daoGroupTopic.save(groupTopic);
		}
		// DAOGroupTopic daoGroupTopic = new DAOGroupTopic();
		// DAOPost daoPost = new DAOPost();
		// List<GroupTopic> liGroupTopics = new ArrayList<GroupTopic>();
		// GroupTopic groupTopic = new GroupTopic();
		// groupTopic.setGroupName("Khoa test");
		// groupTopic.setIdGroupTopic(12);
		// List<Topic> lTopics = new ArrayList<Topic>();
		// Topic topic = new Topic();
		// topic.setGroupTopic(groupTopic);
		// topic.setTitle("Topic test");
		// topic.setIdTopic(52);
		// List<Post> lPosts = new ArrayList<Post>();
		// Post post = new Post();
		// post.setContext("context test");
		// post.setDatePost(new Date());
		// post.setImages(new ArrayList<String>());
		// post.setPoster(new Friend(1, "Nhược Quỳ"));
		// post.setTopic(topic);
		// lPosts.add(post);
		// daoPost.save(post);
		// topic.setListPosts(lPosts);
		// lTopics.add(topic);
		// groupTopic.setLisTopics(lTopics);
	}
}
