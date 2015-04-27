package com.nhuocquy.dao;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;

import com.nhuocquy.dao.exception.DAOException;
import com.nhuocquy.model.Conversation;
import com.nhuocquy.model.MessageChat;

public class DAOMessageChat extends DAO<MessageChat, Long> {
	public DAOMessageChat(Class<MessageChat> entytiClass) {
		super(entytiClass);
		// TODO Auto-generated constructor stub
	}

	public MessageChat getLastMessageChatOfConversation(long idConversattion)
			throws DAOException {
		MessageChat messageChat = null;
		String sqlSearchIdMes = "select idmes  from con_mes where idcon = :idCon ORDER BY idmes DESC limit 0, 1;";
		String hql = "from MessageChat where idmes = :idmes";
		Session session = HibernateUtil.openSession();
		Transaction transaction = null;
		SQLQuery sqlQuery = null;
		Query query = null;
		try {
			transaction = session.getTransaction();
			transaction.begin();
			sqlQuery = session.createSQLQuery(sqlSearchIdMes);
			sqlQuery.setParameter("idCon", idConversattion);
			BigInteger bigInt = (BigInteger) sqlQuery.uniqueResult();
			if (bigInt != null) {
				query = session.createQuery(hql);
				query.setParameter("idmes", bigInt);
				messageChat = (MessageChat) query.uniqueResult();
			}
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			session.close();
		}
		return messageChat;
	}
	public MessageChat testBit(long id, long id2)
			throws DAOException {
		MessageChat messageChat = null;
		Session session = HibernateUtil.openSession();
		Transaction transaction = null;
		SQLQuery sqlQuery = null;
		Query query = null;
		String sql = "select readed from con_mes where idcon = :idcon and idmes = :idmes";
		try {
			transaction = session.getTransaction();
			transaction.begin();
			sqlQuery = session.createSQLQuery(sql);
			sqlQuery.setParameter("idcon", id);
			sqlQuery.setParameter("idmes", id2);
			System.out.println(sqlQuery.uniqueResult().getClass());
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			session.close();
		}
		return messageChat;
	}
	
	public static void main(String[] args) throws DAOException {
		new DAOMessageChat(MessageChat.class)
				.getLastMessageChatOfConversation(2);
		HibernateUtil.shutdown();
	}

}
