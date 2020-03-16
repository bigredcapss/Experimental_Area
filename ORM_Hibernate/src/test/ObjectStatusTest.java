package test;

import bean.User;
import bean.User2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

/**
 * @Description:Hibernate中对象的状态测试
 * @Author:BigRedCaps
 */
public class ObjectStatusTest
{
    private static SessionFactory sf;

    static
    {
        sf = new Configuration().configure().addClass(User2.class).buildSessionFactory();
    }

    /**
     * 对象状态的转换测试
     * @throws Exception
     */
    @Test
    public void testSaveSet () throws Exception
    {
        Session session = sf.openSession();
        session.beginTransaction();

        // 创建对象--【临时状态】
//		User2 user1 = new User2();
//		user1.setUserName("Jack01");
        // 保存操作	--【持久化状态】-->结果会反映到数据库中
//		session.save(user1);
//		user1.setUserName("Jack02");

        // 查询操作
        User2 user2 = (User2) session.get(User2.class, 2);
        user2.setUserName("Jack03");// hibernate会自动与数据库匹配（一级缓存），如果一样就更新数据库

        session.getTransaction().commit();
        //关闭session之后，对象处于游离状态，不在与数据库保持同步
        session.close();

        //该设置不会在数据库中生效，因为session关闭，与数据库同步断开
        user2.setUserName("Jack04");
        // 打印--【游离状态】
        System.out.println(user2.getUserId());
        System.out.println(user2.getUserName());
    }

}
