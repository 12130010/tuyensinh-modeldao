package com.nhuocquy.dao;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;

import com.nhuocquy.dao.exception.DAOException;
import com.nhuocquy.model.Account;
import com.nhuocquy.model.Conversation;
import com.nhuocquy.model.MessageChat;

public class DAOAccount extends DAO<Account, Long>{
	private DAOMessageChat messageChatDAO = new DAOMessageChat(MessageChat.class);
	public DAOAccount(Class<Account> entytiClass) {
		super(entytiClass);
	}
	public Account login(String username, String password) throws DAOException{
		Session session = HibernateUtil.openSession();
		Account ac = null;
		Transaction transaction = null;
		String sql = "select ac from Account as ac where ac.username = :name and ac.password = :pass";
		String sqlCheckReaded = "select readed from con_fri where idcon = :idcon and idfri = :idfri";
		Query query = null;
		SQLQuery sqlQuery = null;
		try {
			transaction = session.getTransaction();
			transaction.begin();
			query = session.createQuery(sql);
			query.setParameter("name", username);
			query.setParameter("pass", password);
			ac = (Account) query.uniqueResult();
			sqlQuery = session.createSQLQuery(sqlCheckReaded);
			sqlQuery.setParameter("idfri", ac.getIdAcc());
			Object ob = null;
			for (Conversation con : ac.getConversations()) {
				sqlQuery.setParameter("idcon", con.getIdCon());
				ob = sqlQuery.uniqueResult();
				con.setReaded(ob == null || ((Boolean) ob).booleanValue() );
			}
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new DAOException(e.getMessage());
		} finally {
			session.close();
		}
		System.out.println(ac.getConversations().size());
//		for (Conversation c : ac.getConversations()) {
//			c.addMessageChat(messageChatDAO.getLastMessageChatOfConversation(c.getIdCon()));
//		}
		return ac;
	}
	public static void main(String[] args) {
		DAOAccount accountDAO = new DAOAccount(Account.class);
		try {
			System.out.println(accountDAO.login("nhuocquy", "nhuocquy"));
		} catch (DAOException e) {
			e.printStackTrace();
		}
		HibernateUtil.shutdown();
	}
}
