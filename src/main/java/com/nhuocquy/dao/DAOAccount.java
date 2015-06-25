package com.nhuocquy.dao;

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
import com.nhuocquy.myfile.MyStatus;

public class DAOAccount extends DAO<Account, Long> {
	private DAOMessageChat messageChatDAO = new DAOMessageChat(
			MessageChat.class);

	public DAOAccount(Class<Account> entytiClass) {
		super(entytiClass);
	}

	public Account login(String username, String password) throws DAOException {
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
			if (ac != null) {
				sqlQuery = session.createSQLQuery(sqlCheckReaded);
				sqlQuery.setParameter("idfri", ac.getIdAcc());
				Object ob = null;
				for (Conversation con : ac.getConversations()) {
					sqlQuery.setParameter("idcon", con.getIdCon());
					ob = sqlQuery.uniqueResult();
					con.setReaded(ob == null || ((Boolean) ob).booleanValue());
				}
			}
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new DAOException(e.getMessage());
		} finally {
			session.close();
		}
		// for (Conversation c : ac.getConversations()) {
		// c.addMessageChat(messageChatDAO.getLastMessageChatOfConversation(c.getIdCon()));
		// }
		return ac;
	}

	public boolean makeFriend(long idacc, long idfri) throws DAOException {
		boolean res = false;
		Session session = HibernateUtil.openSession();
		Transaction transaction = null;
		SQLQuery sqlQuery = null;
		try {
			transaction = session.getTransaction();
			transaction.begin();
			sqlQuery = session
					.createSQLQuery("insert into acc_makefri values (:idacc, :idfri)");
			sqlQuery.setParameter("idacc", idacc);
			sqlQuery.setParameter("idfri", idfri);
			sqlQuery.executeUpdate();
			res = true;
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			res = false;
			throw new DAOException(e.getMessage());
		} finally {
			session.close();
		}
		return res;
	}
	public boolean unMakeFriend(long idacc, long idfri) throws DAOException {
		boolean res = false;
		Session session = HibernateUtil.openSession();
		Transaction transaction = null;
		SQLQuery sqlQuery = null;
		try {
			transaction = session.getTransaction();
			transaction.begin();
			sqlQuery = session
					.createSQLQuery("delete from acc_makefri where idacc = :idacc and idfri = :idfri");
			sqlQuery.setParameter("idacc", idacc);
			sqlQuery.setParameter("idfri", idfri);
			sqlQuery.executeUpdate();
			res = true;
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			res = false;
			throw new DAOException(e.getMessage());
		} finally {
			session.close();
		}
		return res;
	}

	public boolean commitMakeFriend(long idacc, long idfri) throws DAOException {
		boolean res = false;
		Session session = HibernateUtil.openSession();
		Transaction transaction = null;
		SQLQuery sqlQuery = null;
		try {
			transaction = session.getTransaction();
			transaction.begin();
			// insert
			sqlQuery = session
					.createSQLQuery("insert into acc_fri(idacc, idfri) values (:idacc, :idfri)");
			// lan 1
			sqlQuery.setParameter("idacc", idacc);
			sqlQuery.setParameter("idfri", idfri);
			sqlQuery.executeUpdate();
			// lan 2
			sqlQuery.setParameter("idacc", idfri);
			sqlQuery.setParameter("idfri", idacc);
			sqlQuery.executeUpdate();
			// delete
			sqlQuery = session
					.createSQLQuery("delete from acc_makefri where (idacc = :idacc and idfri = :idfri)");
			sqlQuery.setParameter("idacc", idacc);
			sqlQuery.setParameter("idfri", idfri);
			sqlQuery.executeUpdate();
			res = true;
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			res = false;
			throw new DAOException(e.getMessage());
		} finally {
			session.close();
		}
		return res;
	}

	public List<Friend> getListFriend(long idacc) throws DAOException {
		List<Friend> listFriends = null;
		Session session = HibernateUtil.openSession();
		Transaction transaction = null;
		Query query;
		String sql = "from Friend";
		try {
			transaction = session.getTransaction();
			transaction.begin();
			query = session.createQuery(sql);
			listFriends = query.list();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new DAOException(e.getMessage());
		} finally {
			session.close();
		}
		return listFriends;
	}

	

	public MyStatus signup(Account acc) throws DAOException {
		MyStatus myStatus = new MyStatus();
		 Session session = HibernateUtil.openSession();
		 Transaction transaction = null;
		 SQLQuery sqlQuery = null;
		 String sqlCheckExistAccount = "select username from account where username=:username";
		 Query query = null;
		 try {
		 transaction = session.getTransaction();
		 transaction.begin();
		 sqlQuery = session.createSQLQuery(sqlCheckExistAccount);
		 sqlQuery.setParameter(":username", acc.getUsername());
		 
		 transaction.commit();
		 } catch (Exception e) {
		 transaction.rollback();
		 e.printStackTrace();
		 throw new DAOException(e.getMessage());
		 } finally {
		 session.close();
		 }
		return myStatus;
	}
	public static void main(String[] args) {
		DAOAccount accountDAO = new DAOAccount(Account.class);
		try {
			 System.out.println(accountDAO.login("nhuocquy", "nhuocquy"));
			// System.out.println(accountDAO.makeFriend(1, 2));
//			System.out.println(accountDAO.commitMakeFriend(1, 2));
			System.out.println(accountDAO.getListFriend(1));
		} catch (DAOException e) {
			e.printStackTrace();
		}
		HibernateUtil.shutdown();
	}
	
}
