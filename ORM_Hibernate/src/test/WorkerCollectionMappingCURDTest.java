package test;

import bean.Worker;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description:集合映射测试
 * @Author:BigRedCaps
 */
public class WorkerCollectionMappingCURDTest
{
    private static SessionFactory sf;
    static
    {
        sf = new Configuration().configure().addClass(Worker.class).buildSessionFactory();
    }

    /**
     * set集合映射测试--保存操作
     * @throws Exception
     */
    @Test
    public void saveSetTest() throws Exception
    {
        Session session = sf.openSession();
        session.beginTransaction();

        // Set集合对象
        Set<String> addressSet = new HashSet<>();
        addressSet.add("广州");
        addressSet.add("深圳");
        // 工人对象
        Worker worker = new Worker();
        worker.setWorkerName("Jack");
        worker.setAddressSet(addressSet);

        // 保存操作
        session.save(worker);

        session.getTransaction().commit();
        session.close();
    }

    /**
     * List集合映射测试--保存操作
     * @throws Exception
     */
    @Test
    public void saveListTest() throws Exception
    {
        Session session = sf.openSession();
        session.beginTransaction();

        //工人对象
        Worker worker = new Worker();
        worker.setWorkerName("Rose");
		// List集合对象
        worker.getAddressList().add("广州");
        worker.getAddressList().add("深圳");

		// 保存操作
		session.save(worker);

        session.getTransaction().commit();
        session.close();
    }

    /**
     * Map集合映射测试--保存操作
     * @throws Exception
     */
    @Test
    public void saveMapTest() throws Exception
    {
        Session session = sf.openSession();
        session.beginTransaction();

        //工人对象
        Worker worker = new Worker();
        worker.setWorkerName("Rose");

        // Map集合对象对象
        worker.getAddressMap().put("A0001", "广州");
        worker.getAddressMap().put("A0002", "深圳");

        // 保存操作
        session.save(worker);

        session.getTransaction().commit();
        session.close();
    }


    /**
     * 集合数据的获取
     * @throws Exception
     */
    @Test
    public void testGet() throws Exception
    {
        Session session = sf.openSession();
        session.beginTransaction();

        Worker worker = (Worker) session.get(Worker.class, 3); // 及时加载
        System.out.println(worker.getWorkerId());
        System.out.println(worker.getWorkerName());

        // 当查询用户，同时可以获取用户关联的list集合的数据 (因为有正确映射)
        // 当使用到集合数据的使用，才向数据库发送执行的sql语句  (懒加载)
        System.out.println(worker.getAddressList());

        session.getTransaction().commit();
        session.close();
    }
}
