package utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @Description:Hibernate工具类
 * @Author:BigRedCaps
 */
public class HibernateUtil
    {
    private static SessionFactory sf;
    static
    {
        sf = new Configuration().configure().buildSessionFactory();
    }
    /**
     * 创建Session对象
     * @return
     */
    public static Session getSession()
    {
        return sf.openSession();
    }


}
