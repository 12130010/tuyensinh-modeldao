package com.nhuocquy.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;

import com.nhuocquy.dao.exception.DAOException;
import com.nhuocquy.model.Friend;
import com.nhuocquy.model.Post;
import com.nhuocquy.model.Topic;

public class DAOPost extends DAO<Post, Long> {

	public DAOPost() {
		super(Post.class);
	}

	@Override
	public Post findById(Long key) throws DAOException {
		Post post = null;
		Session session = HibernateUtil.openSession();
		Transaction transaction = null;
		// SQLQuery sqlQuery = null;
		Query query = null;
		String sqlSelectPost = "from Post where id= :id";
		try {
			transaction = session.getTransaction();
			transaction.begin();
			//
			query = session.createQuery(sqlSelectPost);
			query.setParameter("id", key);
			post = (Post) query.uniqueResult();
			System.out.println(post);
			//
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			session.close();
		}
		if (post != null) {
			post.getTopic().setListPosts(new ArrayList<Post>());
			post.getTopic().setGroupTopic(null);
		}
		return post;
	}

	@Override
	public Long save(Post post) throws DAOException {
		long id = 0;
		Friend poster = post.getPoster();
		Topic topic = post.getTopic();
		post.setPoster(null);
		post.setTopic(null);
		Session session = HibernateUtil.openSession();
		Transaction transaction = null;
		SQLQuery sqlQuery = null;
		String updatePost = "update post set idtopic = :idtopic, poster_idfriend = :idfriend where idpost = :idpost";
		try {
			transaction = session.getTransaction();
			transaction.begin();
			// insert Post
			// sqlQuery = session.createSQLQuery(sqlInserPost);
			// sqlQuery.setParameter("context", post.getContext());
			// sqlQuery.setParameter("datepost", post.getDatePost());
			// sqlQuery.setParameter("poster_idfriend",
			// post.getPoster().getIdFriend());
			// sqlQuery.setParameter("idtopic", post.getTopic().getIdTopic());
			// sqlQuery.executeUpdate();
			// // insert image of Post
			// sqlQuery = session.createSQLQuery(sqlInsertImageOfPost);
			// sqlQuery.setParameter("idpost", post.getIdPost());
			// List<String> listImage = post.getImages();
			// for (String im : listImage) {
			// sqlQuery.setParameter("image", im);
			// sqlQuery.executeUpdate();
			// }
			id = (Long) session.save(post);
			sqlQuery = session.createSQLQuery(updatePost);
			sqlQuery.setParameter("idpost", id);
			sqlQuery.setParameter("idtopic", topic.getIdTopic());
			sqlQuery.setParameter("idfriend", poster.getIdFriend());
			sqlQuery.executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			session.close();
		}
		return id;
	}

	public Long save2(Post post) throws DAOException {
		return super.save(post);
	}

	public boolean updateLike(Post post) throws DAOException {
		boolean res = false;
		Session session = HibernateUtil.openSession();
		Transaction transaction = null;
		SQLQuery sqlQuery = null;
		// Query query = null;
		String sqlUpdateLike = "update post set clike = :clike, cdislike = :cdislike where idpost = :idpost";
		try {
			transaction = session.getTransaction();
			transaction.begin();
			sqlQuery = session.createSQLQuery(sqlUpdateLike);
			sqlQuery.setParameter("clike", post.getClike());
			sqlQuery.setParameter("cdislike", post.getCdislike());
			sqlQuery.setParameter("idpost", post.getIdPost());
			sqlQuery.executeUpdate();
			transaction.commit();
			res = true;
		} catch (Exception e) {
			res = false;
			transaction.rollback();
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			session.close();
		}
		return res;
	}

	public static void main(String[] args) throws DAOException {
		// System.out.println(new DAOPost().findById(6l).toStringTopic());
		Post post = new Post();
		post.setDatePost(new Date());
		post.setContext("aaaaaaaa");
		post.setPoster(new Friend(1, "Nhuoc Quy2"));
		Topic topic = new DAOTopic().findById(12l);
		post.setTopic(topic);
		new DAOPost().save(post);
	}
}
