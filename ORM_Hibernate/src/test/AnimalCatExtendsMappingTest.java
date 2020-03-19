package test;

import bean.extendsmapping.Animal;
import bean.extendsmapping.Cat;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.util.List;

/**
 * @Description:动物与猫的继承映射测试
 * @Author:BigRedCaps
 */
public class AnimalCatExtendsMappingTest
{
    private static SessionFactory sf;
    static {
        sf = new Configuration()
                .configure()
                .addClass(Cat.class)
                .buildSessionFactory();
    }

    @Test
    public void saveQueryTest() {

        Session session = sf.openSession();
        session.beginTransaction();

        // 保存
//		Cat cat = new Cat();
//		cat.setName("大花猫");
//		cat.setCatchMouse("抓小老鼠");
//		session.save(cat);

        // 获取时候注意：当写hql查询的使用，通过父类查询必须写上类的全名
        Query q = session.createQuery("from bean.Animal");
        List<Animal> list = q.list();
        System.out.println(list);

        session.getTransaction().commit();
        session.close();

    }
}
