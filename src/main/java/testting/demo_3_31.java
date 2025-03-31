package testting;

import java.util.List;

import org.hibernate.Session;
import jakarta.persistence.Query;
import model.product;  // Thêm import cho entity product
import util.hibernat_util;

public class demo_3_31 {
    public static void main(String[] args) {
        Session ss = hibernat_util.getSessionFactory().openSession();

        // Sử dụng createQuery đúng cách
        Query qr = ss.createQuery("select p.id, p.name, p.price FROM product p where p.id = :id", Object[].class);
        
        Query qr1 = ss.createQuery("select p.id, p.name, p.price FROM product p where p.name like :name order by p.id desc", Object[].class);
        
        Query qr2 = ss.createQuery("select c.name, count(p.id), max(p.price) FROM product p inner join category c on p.ct.id_category = c.id group by c.name", Object[].class);
        
        
        qr.setParameter("id", 1);
        
        qr1.setParameter("name", "%5%");
        
        
        List<Object[]> lt = qr2.getResultList();
        
        lt.forEach(p->System.out.printf("%s - %d -  %d\n",p[0],p[1], p[2]));

        ss.close();
    }
    
    
    public static void select1() {
    	Session ss = hibernat_util.getSessionFactory().openSession();

        // Sử dụng createQuery đúng cách
        Query qr = ss.createQuery("select p.id, p.name, p.price FROM product p", Object[].class);
        
        List<Object[]> lt = qr.getResultList();
        
        lt.forEach(p->System.out.printf("%d - %s - %d\n",p[0],p[1], p[2]));

        ss.close();
    }
}
