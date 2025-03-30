package testting;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.cc;
import model.test;
import util.hibernat_util;

public class testCar {
    public static void main(String[] args) {
        SessionFactory sessionFactory = hibernat_util.getSessionFactory();
        if (sessionFactory != null) {
            Session session = null;
            Transaction transaction = null;
            try {
                session = sessionFactory.openSession();
                transaction = session.beginTransaction();

                // Thêm dữ liệu
                test dog1 = new test();
                dog1.setName("Dog423");
                
             // Thêm dữ liệu
                cc dog12 = new cc();
                dog12.setName("Dog423");
                

                session.save(dog1);
                session.save(dog12);
                transaction.commit(); // Commit giao dịch
                System.out.println("Lưu thành công!");
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback(); // Rollback nếu có lỗi
                }
                System.err.println("Lỗi khi lưu dữ liệu:");
                e.printStackTrace();
            } finally {
                if (session != null) {
                    session.close();
                }
                sessionFactory.close(); // Đóng SessionFactory sau khi hoàn tất
            }
        } else {
            System.out.println("Không thể khởi tạo SessionFactory.");
        }
    }
}