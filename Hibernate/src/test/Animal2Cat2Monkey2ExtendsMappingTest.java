package test;

import bean.extendsmapping.Animal2;
import bean.extendsmapping.Cat2;
import bean.extendsmapping.Monkey2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

/**
 * @Description:继承映射的第三种配置方式测试
 * @Author:BigRedCaps
 */
public class Animal2Cat2Monkey2ExtendsMappingTest
{
    private static SessionFactory sf;
    static {
        sf = new Configuration()
                .configure()
                .addClass(Animal2.class)
                .buildSessionFactory();
    }

    @Test
    public void getSave() {

        Session session = sf.openSession();
        session.beginTransaction();

        // 保存
        Cat2 cat = new Cat2();
        cat.setName("大花猫");
        cat.setCatchMouse("抓小老鼠");

        Monkey2 m = new Monkey2();
        m.setName("猴子");
        m.setEatBanana("吃10个香蕉");

        // 保存
        session.save(cat);
        session.save(m);

        session.getTransaction().commit();
        session.close();

    }
}
