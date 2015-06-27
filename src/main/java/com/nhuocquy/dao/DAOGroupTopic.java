package com.nhuocquy.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;

import com.nhuocquy.dao.exception.DAOException;
import com.nhuocquy.model.GroupTopic;
import com.nhuocquy.model.Post;
import com.nhuocquy.model.Topic;

public class DAOGroupTopic extends DAO<GroupTopic, Long> {

	public DAOGroupTopic() {
		super(GroupTopic.class);
	}

	@Override
	public GroupTopic findById(Long key) throws DAOException {
		GroupTopic res = null;
		List<Topic> listtTopics = null;
		Session session = HibernateUtil.openSession();
		Transaction transaction = null;
		// SQLQuery sqlQuery = null;
		Query query = null;
		String sqlSelectGrTopic = "from GroupTopic where id= :id";
		String sqlSelectListTopic = "from Topic where idgroup= :idgroup";
		try {
			transaction = session.getTransaction();
			transaction.begin();
			//
			query = session.createQuery(sqlSelectGrTopic);
			query.setParameter("id", key);
			res = (GroupTopic) query.uniqueResult();
			//
			query = session.createQuery(sqlSelectListTopic);
			query.setParameter("idgroup", key);
			listtTopics = query.list();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			session.close();
		}
		if (listtTopics != null)
			for (Topic topic : listtTopics) {
				topic.setListPosts(new ArrayList<Post>());
				topic.setGroupTopic(null);
			}
		res.setLisTopics(listtTopics);
		return res;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GroupTopic> selectAll() throws DAOException {
		List<GroupTopic> list = null;
		Session session = HibernateUtil.openSession();
		Transaction transaction = null;
		// SQLQuery sqlQuery = null;
		Query query = null;
		String sqlSelectAllGroup = "from GroupTopic";
		try {
			transaction = session.getTransaction();
			transaction.begin();
			query = session.createQuery(sqlSelectAllGroup);
			list = query.list();

			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			session.close();
		}
		if (list != null)
			for (GroupTopic g : list) {
				g.setLisTopics(new ArrayList<Topic>());
			}
		return list;
	}

	public static void main(String[] args) throws DAOException {
		DAOGroupTopic d = new DAOGroupTopic();
//		System.out.println(d.selectAll());
		 System.out.println(d.findById(8l).toStringListTopic());
	}

}
