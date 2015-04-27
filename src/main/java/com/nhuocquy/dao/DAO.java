package com.nhuocquy.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nhuocquy.dao.exception.DAOException;

import util.HibernateUtil;

public abstract class DAO<T, E> implements GeneralDAO<T, E> {
	protected Class<T> entytiClass;

	public DAO(Class<T> entytiClass) {
		this.entytiClass = entytiClass;
	}

	@SuppressWarnings("unchecked")
	public List<T> selectAll() throws DAOException {
		Session session = HibernateUtil.openSession();
		List<T> list = null;
		Transaction transaction = null;
		try {
			transaction = session.getTransaction();
			transaction.begin();
			Query query = session.createQuery("FROM "
					+ entytiClass.getSimpleName());
			query.setFetchSize(0);
			list = query.list();
			transaction.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			transaction.rollback();
			throw new DAOException(e.getMessage());
		} finally {
			session.close();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public T merge(T object) throws DAOException {
		Session session = HibernateUtil.openSession();
		T t = null;
		Transaction transaction = null;
		try {
			transaction = session.getTransaction();
			transaction.begin();
			t = (T) session.merge(object);
			transaction.commit();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			transaction.rollback();
			throw new DAOException(e.getMessage());
		} finally {
			session.close();
		}
		return t;
	}

	public boolean update(T object) throws DAOException {
		Session session = HibernateUtil.openSession();
		Transaction transaction = null;
			try {
				transaction = session.getTransaction();
				transaction.begin();
				session.saveOrUpdate(object);
				transaction.commit();
				return true;
			} catch (Exception e) {
				transaction.rollback();
				throw new DAOException(e.getMessage());
			} finally {
				session.close();
			}
	}

	public boolean delete(T object) throws DAOException {
		Session session = HibernateUtil.openSession();
		Transaction transaction = null;
			try {
				transaction = session.getTransaction();
				transaction.begin();
				session.delete(object);
				transaction.commit();
				return true;
			} catch (Exception e) {
				transaction.rollback();
				throw new DAOException(e.getMessage());
			} finally {
				session.close();
			}
	}

	@SuppressWarnings("unchecked")
	public T findById(E key) throws DAOException {
		Session session = HibernateUtil.openSession();
		T t = null;
		Transaction transaction = null;
		try {
			transaction = session.getTransaction();
			transaction.begin();
			t = (T) session.get(entytiClass, (Serializable) key);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new DAOException(e.getMessage());
		} finally {
			session.close();
		}
		return t;
	}
	// Session session = HibernateUtil.openSession();
	// Transaction transaction = null;
	// SQLQuery sqlQuery = null;
	// Query query = null;
	// try {
	// transaction = session.getTransaction();
	// transaction.begin();
	//
	// transaction.commit();
	// } catch (Exception e) {
	// transaction.rollback();
	// e.printStackTrace();
	// throw new DAOException(e.getMessage());
	// } finally {
	// session.close();
	// }

}
