package com.nhuocquy.dao;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;

import com.nhuocquy.dao.exception.DAOException;
import com.nhuocquy.model.Account;
import com.nhuocquy.model.Conversation;
import com.nhuocquy.model.Friend;
import com.nhuocquy.model.MessageChat;

public class DAOConversation extends DAO<Conversation, Long> {
	private DAOFriend friendDAO = new DAOFriend(Friend.class);
	private DAOMessageChat messageChatDAO = new DAOMessageChat(
			MessageChat.class);

	public DAOConversation(Class<Conversation> entytiClass) {
		super(entytiClass);
	}

	@Override
	public Conversation findById(Long key) throws DAOException {
		Session session = HibernateUtil.openSession();
		Transaction transaction = null;
		Query query = null;
		String sqlSearchCon = "from " + Conversation.class.getName()
				+ " where idcon = :idcon";
		String setReaded = "update con_fri set readed = :readed  where idcon = :idcon";
		SQLQuery sqlQuery = null;
		Conversation conversation = null;
		try {
			transaction = session.getTransaction();
			transaction.begin();
			query = session.createQuery(sqlSearchCon);
			query.setParameter("idcon", key);
			conversation = (Conversation) query.uniqueResult();
			sqlQuery = session.createSQLQuery(setReaded);
			sqlQuery.setParameter("readed", 1);
			sqlQuery.setParameter("idcon", key);
			sqlQuery.executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			session.close();
		}
		return conversation;
	}

	public Conversation getConversation(long... ids) throws DAOException {
		Session session = HibernateUtil.openSession();
		Transaction transaction = null;
		SQLQuery sqlQuery = null;
		Query query = null;
		String sqlAllIdcon = "select idcon from con_fri where idcon in (select idcon from con_fri where idfri  = :idfri0) GROUP BY idcon HAVING count(*) = :len ";
		String sqlSearchCon = "from " + Conversation.class.getName()
				+ " where idcon = :idcon";
		Conversation conversation = null;
		try {
			transaction = session.getTransaction();
			transaction.begin();
			sqlQuery = session.createSQLQuery(sqlAllIdcon);
			sqlQuery.setParameter("len", ids.length);
			sqlQuery.setParameter("idfri0", ids[0]);
			List<BigInteger> list = sqlQuery.list();
			int lenIDs = ids.length - 1;
			query = session.createQuery(sqlSearchCon);
			listCon: for (BigInteger l : list) {
				query.setParameter("idcon", l);
				conversation = (Conversation) query.uniqueResult();
				// conversation = findById(l.longValue());
				for (int i = 0; i <= lenIDs; i++) {
					if (!conversation.containIDFri(ids[i])) {
						conversation = null;
						break;
					}
					if (i == lenIDs) {
						break listCon;
					}
				}
			}
			if (conversation == null) { // không tìm thấy conversation
				conversation = new Conversation();
				conversation.setFriends(friendDAO.getList(ids));
				conversation = (Conversation) session.merge(conversation);
				// luu convertation vao accoutn
				String sqlSelectAccout = "insert into acc_con (idacc, idcon) values (:idacc, :idcon)";
				sqlQuery = session.createSQLQuery(sqlSelectAccout);
				sqlQuery.setParameter("idcon", conversation.getIdCon());
				for (long l : ids) {
					sqlQuery.setParameter("idacc", l);
					sqlQuery.executeUpdate();
				}
			} else { // bổ sung code lấy danh sách tin nhắn giới hạn
				// TODO Bá»— sung code chá»‰ láº¥y tin nháº¯n cuá»‘i
				// MessageChat messageChat =
				// messageChatDAO.getLastMessageChatOfConversation(conversation.getIdCon());
				// if(messageChat != null){
//				 conversation.addMessageChat(messageChat);
				// }
			}
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			session.close();
		}
		return conversation;
	}

	/**
	 * Save all MessageChat in Conversation into database
	 * 
	 * @param conversation
	 *            Conversation containt MessageChat
	 * @throws DAOException
	 */
	public void saveMessageChatInConversation(Conversation conversation)
			throws DAOException {
		Session session = HibernateUtil.openSession();
		Transaction transaction = null;
		SQLQuery sqlQuery = null;
		Query query = null;
		String sqlInserMesCon = "insert into con_mes (idcon, idmes) values (:idcon, :idmes)";
		try {
			transaction = session.getTransaction();
			transaction.begin();
			sqlQuery = session.createSQLQuery(sqlInserMesCon);
			for (MessageChat m : conversation.getListMes()) {
				session.persist(m);
				sqlQuery.setParameter("idcon", conversation.getIdCon());
				sqlQuery.setParameter("idmes", m.getIdMes());
				sqlQuery.executeUpdate();
			}
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			session.close();
		}
	}

	

	public static void main(String[] args) throws DAOException {
		System.out.println(new DAOConversation(Conversation.class)
				.findById(6l));
		System.out
				.println(new DAOConversation(Conversation.class).findById(1l));
		HibernateUtil.shutdown();
	}
}
