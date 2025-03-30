package testting;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import model.category;
import model.manager;
import model.product;
import util.hibernat_util;

public class demo_3_29 {
    public static void main(String[] args) {
        Session ss = hibernat_util.getSessionFactory().openSession();
        Transaction tx = ss.beginTransaction(); // Bắt đầu transaction
        
        category ct = ss.get(category.class, 1);
        
        manager mn = ss.get(manager.class, 1);
//        mn.setName("Dao Hoang My Tien");
//        mn.setCountry("Viet Nam");
        mn.getProducts().forEach(p -> System.out.printf("%tF - %s\n", p.getDateProduct(), p.getName()));
        
        // Sửa lỗi khi in Date
//        ct.getProducts().forEach(p -> System.out.printf("%tF - %s\n", p.getDateProduct(), p.getName()));

//        Set<manager> list_mana = new HashSet<manager>();
//        list_mana.add(ss.get(manager.class, 1));
//        list_mana.add(ss.get(manager.class, 2));
        // Nếu muốn thêm sản phẩm mới
//        product pt = new product();
//        pt.setName("Oppo A52");
//        pt.setCt(ct);
//        pt.setDateProduct(new Date());
//        pt.setManager(list_mana);
        
        
        ss.persist(mn); // Lưu sản phẩm mới

        tx.commit(); // Xác nhận transaction
        ss.close(); // Đóng session
    }
}
