package testting;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import model.category;
import model.manager;
import model.product;
import util.hibernat_util;

public class demo_3_30 {
	 public static void main(String[] args) {
	        Session ss = hibernat_util.getSessionFactory().openSession();
	        Transaction tx = ss.beginTransaction(); // Bắt đầu transaction
	        
	        
	        
	        
	        tx.commit(); // Xác nhận transaction
	        ss.close(); // Đóng session
	    }
	 
	 public static void selectAll() {
		 Session ss = hibernat_util.getSessionFactory().openSession();
	        Transaction tx = ss.beginTransaction(); // Bắt đầu transaction
	        
	        CriteriaBuilder build = ss.getCriteriaBuilder();
	        CriteriaQuery<product> query = build.createQuery(product.class);
	        Root root = query.from(product.class);
	        
	        query.select(root);
	        
//	        Predicate p1 = build.like(root.get("name").as(String.class), "%Pro%");
//	        Predicate p2 = build.like(root.get("name").as(String.class), "%Oppo%");
	        
//	        Predicate p1 = build.greaterThanOrEqualTo(root.get("price").as(Integer.class), 200000);
//	        Predicate p2 = build.lessThanOrEqualTo(root.get("price").as(Integer.class), 400000);
	        
	        Predicate p1 = build.between(root.get("price").as(Integer.class), 200000, 400000);
	        
//	        query.where(build.and(p1,p2));
	        
	        query.where(p1);
	        
	        Query q = ss.createQuery(query);
	        
	        List<product> lst = q.getResultList();
	        
	        lst.forEach(p -> System.out.printf("%d - %s\n", p.getId(), p.getName()));

	        
	        
//	        ss.persist(mn); // Lưu sản phẩm mới

	        tx.commit(); // Xác nhận transaction
	        ss.close(); // Đóng session
	 }
	 
	 public static void asGroupBy(){
		 Session ss = hibernat_util.getSessionFactory().openSession();
	        Transaction tx = ss.beginTransaction(); // Bắt đầu transaction
	        
	        CriteriaBuilder build = ss.getCriteriaBuilder();
	        CriteriaQuery<Object[]> query = build.createQuery(Object[].class);
	        Root root = query.from(product.class);
	        
	        query = query.multiselect(build.count(root.get("id").as(Integer.class)),build.max(root.get("price").as(Integer.class)));
	        
	        Query qr = ss.createQuery(query);
	        Object[] kq = (Object[]) qr.getSingleResult();
	        
	        System.out.println("Tong so id: "+kq[0]);
	        System.out.println("So tien cao nhat co la: "+kq[1]);
	        
	        tx.commit(); // Xác nhận transaction
	        ss.close(); // Đóng session
	 }
	 
	 public static void joinTwoTable() {
		 Session ss = hibernat_util.getSessionFactory().openSession();
	        Transaction tx = ss.beginTransaction(); // Bắt đầu transaction
	        
	        CriteriaBuilder build = ss.getCriteriaBuilder();
	        CriteriaQuery<Object[]> query = build.createQuery(Object[].class);
	        Root<product> pr = query.from(product.class);
	        Root<category> cr = query.from(category.class);
	        
	        // select c.name, count(p.id), max(p.price) from category c inner join product p on c.id = p.id_category group by c.name order by c.name asc
	        
	        
	        query.where(build.equal(pr.get("ct").get("id_category"), cr.get("id_category")));

	        
	        query = query.multiselect(cr.get("name").as(String.class), 
	        						build.count(pr.get("id").as(Integer.class)),
	        						build.max(pr.get("price").as(Integer.class)));
	        
	        query = query.groupBy(cr.get("name").as(String.class));
	        query = query.orderBy(build.asc(cr.get("name").as(String.class)));
	        
	        Query q = ss.createQuery(query);
	        
	        List<Object[]> kq = q.getResultList();
	        kq.forEach(k -> {
	        	System.out.printf("%s - count: %d - Max: %d\n", k[0], k[1], k[2]);

	        });
	        
	        
	        tx.commit(); // Xác nhận transaction
	        ss.close(); // Đóng session
	 }
	 
}
