package test;

import bean.manytoone.Dept;
import bean.manytoone.Employee2;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

/**
 * @Description:Hibernate的二级缓存测试
 * @Author:BigRedCaps
 */
public class HibernateSecondCacheTest
{
    private static SessionFactory sf;
    static {
        sf = new Configuration()
                .configure()
                .addClass(Dept.class)
                .addClass(Employee2.class)
                .buildSessionFactory();
    }
    // 1. 测试二级缓存的使用
    // 没有/有用 二级缓存
    @Test
    public void testCache() {
        Session session1 = sf.openSession();
        session1.beginTransaction();
        // a. 查询一次
        Dept dept = (Dept) session1.get(Dept.class, 3);
        dept.getEmps().size();// 集合
        session1.getTransaction().commit();
        session1.close();

        System.out.println("------");

        // 第二个session
        Session session2 = sf.openSession();
        session2.beginTransaction();
        // a. 查询一次
        dept = (Dept) session2.get(Dept.class, 3);  // 二级缓存配置好； 这里不查询数据库
        dept.getEmps().size();

        session2.getTransaction().commit();
        session2.close();
    }


    @Test
    public void listCache() {
        Session session1 = sf.openSession();
        session1.beginTransaction();
        // HQL查询  【setCacheable  指定从二级缓存找，或者是放入二级缓存】
        Query q = session1.createQuery("from Dept").setCacheable(true);
        System.out.println(q.list());
        session1.getTransaction().commit();
        session1.close();

        System.out.println("--------------------------------");

        Session session2 = sf.openSession();
        session2.beginTransaction();
        q = session2.createQuery("from Dept").setCacheable(true);
        System.out.println(q.list());  // 不查询数据库： 需要开启查询缓存
        session2.getTransaction().commit();
        session2.close();
    }
}
