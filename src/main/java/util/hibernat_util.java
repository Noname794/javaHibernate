package util;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class hibernat_util {
    private static final SessionFactory sessionFactory;

    static {
        try {
            // Tạo SessionFactory từ hibernate.cfg.xml
            sessionFactory = new Configuration().configure("hibernate.cfg2.xml").buildSessionFactory();
        } catch (Exception e) {
            System.err.println("Không thể khởi tạo SessionFactory:");
            e.printStackTrace();
            throw new ExceptionInInitializerError(e); // Ném lỗi nếu thất bại
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}