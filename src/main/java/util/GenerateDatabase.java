package util;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nhuocquy.model.Account;
import com.nhuocquy.model.Conversation;
import com.nhuocquy.model.Friend;



public class GenerateDatabase {
	public static void main(String[] args) {
		Account ac1 = new Account();
		ac1.setName("Trang");
		ac1.setUsername("trang");
		ac1.setPassword("trang");
		Account ac2 = new Account();
		ac2.setName("Tuyet");
		ac2.setUsername("tuyet");
		ac2.setPassword("tuyet");
		Account ac3 = new Account();
		ac3.setName("Nhuoc Quy");
		ac3.setUsername("nhuocquy");
		ac3.setPassword("nhuocquy");
		//
		List<Friend> listFre = new ArrayList<Friend>();
		Friend fre = new Friend();
		fre.setName("Trang");
		listFre.add(fre);
		 fre = new Friend();
		fre.setName("Tuyet");
		listFre.add(fre);
		fre = new Friend();
		fre.setName("Nhuoc Quy");
		listFre.add(fre);
		Conversation con = new Conversation();
		con.setFriends(listFre);
//		
		List<Conversation> lisConversations = new ArrayList<Conversation>();
		lisConversations.add(con);
		ac1.setListFrs(listFre);
		ac1.setConversations(lisConversations);
		
		Session session = HibernateUtil.openSession();
		Transaction transaction = null;
		
		try {
			transaction = session.getTransaction();
			transaction.begin();
			session.persist(ac1);
			session.persist(ac2);
			session.persist(ac3);
			transaction.commit();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			transaction.rollback();
		} finally {
			session.close();
			HibernateUtil.shutdown();
		}


	}
}
