package test;

import bean.User2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

/**
 * @Description:Session缓存测试
 * @Author:BigRedCaps
 */
public class SessionCacheTest
{
    private static SessionFactory sf;
    static
    {
        sf = new Configuration().configure().addClass(User2.class).buildSessionFactory();
    }

    /**
     * Hibernate一级缓存测试
     * @throws Exception
     */
    @Test
    public void cacheTest() throws Exception {
        Session session = sf.openSession();
        session.beginTransaction();
        User2 user = null;
        // 查询操作,打印一条sql,第二次查询从缓存中取
        user = (User2) session.get(User2.class, 2);// 先检查缓存中是否有数据，如果有不查询数据库，直接从缓存中获取
        user = (User2) session.get(User2.class, 2);// 先检查缓存中是否有数据，如果有不查询数据库，直接从缓存中获取

        session.getTransaction().commit();
        session.close();
    }

    /**
     * session的flush方法测试（使一级缓存与数据库同步）
     * @throws Exception
     */
    @Test
    public void flushTest() throws Exception {
        Session session = sf.openSession();
        session.beginTransaction();

        User2 user = null;
        user = (User2) session.get(User2.class, 2);
        user.setUserName("Tom");
        // 缓存数据与数据库同步
        session.flush();

        user.setUserName("Tom_new");

        session.getTransaction().commit();  // session.flush();
        session.close();
    }

    /**
     * session的evit、clear方法测试
     * @throws Exception
     */
    @Test
    public void clearTest() throws Exception {
        Session session = sf.openSession();
        session.beginTransaction();

        User2 user = null;
        // 查询操作
        user = (User2) session.get(User2.class, 2);
        // 清空缓存内容
//		session.clear(); // 清空缓存中的所有对象
        session.evict(user);// 清除缓存中的指定对象

        user = (User2) session.get(User2.class, 5);

        session.getTransaction().commit();  // session.flush();
        session.close();
    }

    /**
     * 不同session不会共享缓存数据测试
     * @throws Exception
     */
    @Test
    public void sessionTest() throws Exception {
        Session session1 = sf.openSession();
        session1.beginTransaction();
        Session session2 = sf.openSession();
        session2.beginTransaction();

        // user放入session1的缓存区
        User2 user = (User2) session1.get(User2.class, 1);
        // user放入session2的缓存区
        session2.update(user);

        // 修改对象
        user.setUserName("New Name");  // session1与session2是两个缓存区，所以会有2条update语句

        session1.getTransaction().commit();  // 相当于session1.flush();
        session1.close();
        session2.getTransaction().commit();  // 相当于session2.flush();
        session2.close();
    }
}
