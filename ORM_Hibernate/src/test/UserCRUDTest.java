package test;

import bean.compositekeymapping.CompositeKeysBean;
import bean.compositekeymapping.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

/**
 * @Description:用户实体的CRUD测试
 * @Author:BigRedCaps
 */
public class UserCRUDTest
{
    private static SessionFactory sf;
    static
    {
        sf = new Configuration().configure().addClass(User.class).buildSessionFactory();
    }

    /**
     * 保存对象
     * @throws Exception
     */
    @Test
    public void testSave() throws Exception
    {
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        // 复合主键对象
        CompositeKeysBean keys = new CompositeKeysBean();
        keys.setAddress("广州棠东");
        keys.setUserName("Jack");
        User user = new User();
        user.setAge(20);
        user.setKeys(keys);

        // 保存
        session.save(user);

        tx.commit();
        session.close();
    }

    @Test
    public void testGet() throws Exception
    {
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        //构建主键再查询
        CompositeKeysBean keys = new CompositeKeysBean();
        keys.setAddress("广州棠东");
        keys.setUserName("Jack");

        // 主键查询
        User user = (User) session.get(User.class, keys);
        // 测试输出
        if (user != null){
            System.out.println(user.getKeys().getUserName());
            System.out.println(user.getKeys().getAddress());
            System.out.println(user.getAge());
        }

        tx.commit();
        session.close();
    }
}
