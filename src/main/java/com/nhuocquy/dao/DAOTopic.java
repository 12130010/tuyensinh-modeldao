package com.nhuocquy.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;

import com.nhuocquy.dao.exception.DAOException;
import com.nhuocquy.model.GroupTopic;
import com.nhuocquy.model.Post;
import com.nhuocquy.model.Topic;

public class DAOTopic extends DAO<Topic, Long> {

	public DAOTopic() {
		super(Topic.class);
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}

	@Override
	public Topic findById(Long key) throws DAOException {
		Topic topic = null;
		List<Post> lPosts = null;
		Session session = HibernateUtil.openSession();
		Transaction transaction = null;
		// SQLQuery sqlQuery = null;
		Query query = null;
		String sqlSelectGrTopic = "from Topic where id= :id";
		String sqlSelectListTopic = "from Post where idtopic= :idtopic";
		try {
			transaction = session.getTransaction();
			transaction.begin();
			//
			query = session.createQuery(sqlSelectGrTopic);
			query.setParameter("id", key);
			topic = (Topic) query.uniqueResult();
			//
			query = session.createQuery(sqlSelectListTopic);
			query.setParameter("idtopic", key);
			lPosts = query.list();
//			lPosts = topic.getListPosts();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			session.close();
		}
		for (Post post : lPosts) {
			post.setTopic(null);
		}
		if (topic != null) {
			topic.setListPosts(lPosts);
			topic.getGroupTopic().setLisTopics(new ArrayList<Topic>());
		}
		return topic;
	}

	public static void main(String[] args) throws DAOException {
//		 DAOPost daoPost = new DAOPost();
		DAOTopic daoTopic = new DAOTopic();
		 System.out.println(daoTopic.findById(2l));
//		Topic topic = new Topic();
		// topic.setTitle("Thầy ơi cho em hỏi2");
		// topic.setListPosts(new ArrayList<Post>());
		//
		// GroupTopic groupTopic = new GroupTopic();
		// groupTopic.setGroupName("Khoa CNTT");
		// groupTopic.addTopic(topic);
		//
		// topic.setGroupTopic(groupTopic);
		// DAOTopic t = new DAOTopic();
		// topic = t.merge(topic);
		// List<Post> liPosts =new ArrayList<Post>();
		// topic.setListPosts(liPosts);
		// for (int i = 0; i < 3; i++) {
		// Post p = new Post();
		// p.setContext("Hello: " + i);
		// p.setDatePost(new Date());
		// p.setTopic(topic);
		// p.setPoster(new Friend(1, "Nhuoc Quy"));
		// daoPost.save(p);
		// }
		// t.save(topic);
		// topic = new Topic();
		// topic.setTitle("Thầy ơi cho em hỏi2");
		// topic.setListPosts(new ArrayList<Post>());
		//
		// groupTopic.addTopic(topic);
		//
		// topic.setGroupTopic(groupTopic);
		// t.save(topic);
		// topic.setIdTopic(10);
		// System.out.println(topic.toStringListPostAndGroup());
		// topic = t.merge(topic);
		// System.out.println(topic.toStringListPostAndGroup());
		// HibernateUtil.shutdown();
	}
}
