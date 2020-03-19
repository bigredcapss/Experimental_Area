package test;

import bean.extendsmapping.Animal3;
import bean.extendsmapping.Cat3;
import bean.extendsmapping.Monkey3;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

/**
 * @Description:继承映射的第四种配置方式测试
 * @Author:BigRedCaps
 */
public class Animal3Cat3Monkey3ExtendsMappingTest
{
    private static SessionFactory sf;
    static {
        sf = new Configuration()
                .configure()
                .addClass(Animal3.class)
                .buildSessionFactory();
    }

    @Test
    public void getSave() {

        Session session = sf.openSession();
        session.beginTransaction();

        // 保存
        Cat3 cat = new Cat3();
        cat.setName("大花猫");
        cat.setCatchMouse("抓小老鼠");

        Monkey3 m = new Monkey3();
        m.setName("猴子");
        m.setEatBanana("吃10个香蕉");

        // 保存
        session.save(cat);
        session.save(m);

        session.getTransaction().commit();
        session.close();

    }
}
