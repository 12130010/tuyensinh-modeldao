package com.nhuocquy.dao;

import java.util.ArrayList;
import java.util.List;

import com.nhuocquy.dao.exception.DAOException;
import com.nhuocquy.model.Friend;

public class DAOFriend extends DAO<Friend, Long> {

	public DAOFriend(Class<Friend> entytiClass) {
		super(entytiClass);
	}
	public List<Friend> getList(long... ids) throws DAOException{
		List<Friend> list = new ArrayList<Friend>();
		for (long id : ids) {
			list.add(findById(id));
		}
		return list;
	}
	public static void main(String[] args) throws DAOException {
		DAOFriend daoFriend = new DAOFriend(Friend.class);
		System.out.println(daoFriend.getList(1l,2l));
	}
}
