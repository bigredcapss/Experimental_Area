package test;

import bean.extendsmapping.Animal;
import bean.extendsmapping.Cat;
import bean.extendsmapping.Monkey;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

/**
 * @Description:继承映射的第二种配置方式（映射到父类Animal中）测试
 * @Author:BigRedCaps
 */
public class AnimalMonkeyExtendsMappingTest
{
    private static SessionFactory sf;
    static {
        sf = new Configuration()
                .configure()
                .addClass(Animal.class)
                .buildSessionFactory();
    }

    @Test
    public void saveTest() {

        Session session = sf.openSession();
        session.beginTransaction();

        // 保存
        Cat cat = new Cat();
        cat.setName("大花猫");
        cat.setCatchMouse("抓小老鼠");

        Monkey m = new Monkey();
        m.setName("猴子");
        m.setEatBanana("吃10个香蕉");

        // 保存
        session.save(cat);
        session.save(m);

        session.getTransaction().commit();
        session.close();

    }
}
