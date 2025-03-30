package testting;

import org.hibernate.Session;

import jakarta.transaction.Transaction;
import model.product;
import util.hibernat_util;

public class test_other {
	public static void main(String[] args) {
		Session ss = hibernat_util.getSessionFactory().openSession();
		
		org.hibernate.Transaction tr = ss.beginTransaction();
		
		updatePrice(ss, 1, 100000);
		updatePrice(ss, 2, 200000);
		updatePrice(ss, 3, 300000);
		updatePrice(ss, 4, 400000);
		
		tr.commit();
		ss.close();
		
	}
	
	public static void updatePrice(Session s, int id, int price) {
		product pt = s.get(product.class, id);
		
		if(pt != null) {
			pt.setPrice(price);
			s.merge(pt);
		}
		else {
			System.out.println("Doesn't have "+id);
		}
	}
}
