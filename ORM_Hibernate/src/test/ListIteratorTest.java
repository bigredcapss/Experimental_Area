package test;

import bean.User2;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

/**
 * @Description:list()与iterator()测试
 * @Author:BigRedCaps
 */
public class ListIteratorTest
{
    private static SessionFactory sf;
    static
    {
        sf = new Configuration().configure().addClass(User2.class).buildSessionFactory();
    }
    /**
     * list与iterator区别
     * 1. list 方法
     * 2. iterator 方法
     * 3. 缓存
     * @throws Exception
     */
    /**
     * list 方法测试:一次把所有的记录都查询出来；会放入缓存，但不会从缓存中获取数据
     */
    @Test
    public void listTest() throws Exception {
        Session session = sf.openSession();
        session.beginTransaction();
        // HQL查询
        Query q = session.createQuery("from User2 ");
        // list()方法
        List<User2> list = q.list();

        for (int i=0; i<list.size(); i++){
            System.out.println(list.get(i));
        }

        session.getTransaction().commit();
        session.close();
    }

    /**
     * iterator 方法测试：N+1查询； N表示所有的记录总数
     * 即会先发送一条语句查询所有记录的主键（1），
     * 再根据每一个主键再去数据库查询（N）！
     * 会放入缓存，也会从缓存中取数据！
     */
    @Test
    public void iteratorTest() throws Exception {
        Session session = sf.openSession();
        session.beginTransaction();
        // HQL查询
        Query q = session.createQuery("from User2 ");
        // iterator()方法
        Iterator<User2> it = q.iterate();
        while(it.hasNext()){
            // 得到当前迭代的每一个对象
            User2 user = it.next();
            System.out.println(user);
        }
        session.getTransaction().commit();
        session.close();
    }


    /**
     * 通过观察Hibernate执行时打印的sql语句，测试list()与iterator()方法执行时，是否会把结果放入session缓存，一级是否会从
     * session缓存中取；
     * @throws Exception
     */
    @Test
    public void cacheTest() throws Exception {
        Session session = sf.openSession();
        session.beginTransaction();

        /**************执行2次list【注意观察hibernate执行时的sql语句】*****************/
         Query q = session.createQuery("from User2");
         List<User2> list = q.list();      // 【会放入缓存】
         for (int i=0; i<list.size(); i++){
         System.out.println(list.get(i));
         }
         System.out.println("=========list===========");
         list = q.list();				// 【不会从缓存中取】
         for (int i=0; i<list.size(); i++){
         System.out.println(list.get(i));
         }

         /**************执行2次iteator【注意观察Hibernate执行时的sql语句】******************/
        /*Query q = session.createQuery("from User2 ");
        Iterator<User2> it = q.iterate();		// 【放入缓存】
        while(it.hasNext()){
            User2 user = it.next();
            System.out.println(user);
        }
        System.out.println("==========iterate===========");
        it = q.iterate();						// 【也会从缓存中取】
        while(it.hasNext()){
            User2 user = it.next();
            System.out.println(user);
        }*/

        session.getTransaction().commit();
        session.close();
    }

    /**
     * 测试list方法会放入缓存,iterator()方法会从缓存中取；
     * @throws Exception
     */
    @Test
    public void list_IteratorTest() throws Exception {
        Session session = sf.openSession();
        session.beginTransaction();

        // 得到Query接口的引用
        Query q = session.createQuery("from User2 ");

        // 先list  【会放入缓存，但不会从缓存中获取数据】
        List<User2> list = q.list();
        for (int i=0; i<list.size(); i++){
            System.out.println(list.get(i));
        }

        // 再iteraotr  【会放入缓存，也会从缓存中取】
        Iterator<User2> it = q.iterate();
        while(it.hasNext()){
            User2 user = it.next();
            System.out.println(user);
        }

        session.getTransaction().commit();
        session.close();
    }
}
