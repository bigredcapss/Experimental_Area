package test;

import bean.onetoone.IdCard2;
import bean.onetoone.People2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

/**
 * @Description:People2与IdCard2一对一映射----基于主键映射的配置测试
 * @Author:BigRedCaps
 */
public class PeopleIdCardOneToOne2Test
{
    private static SessionFactory sf;
    static {
        sf = new Configuration()
                .configure()
                .addClass(IdCard2.class)
                .addClass(People2.class)
                .buildSessionFactory();
    }

    /**
     * 一对一保存测试--基于主键的映射
     */
    @Test
    public void getSave() {

        Session session = sf.openSession();
        session.beginTransaction();

        // 用户
        People2 people2 = new People2();
        people2.setPeopleName("Jack");
        // 身份证
        IdCard2 idCard = new IdCard2();
        idCard.setCardNum("441202XXX");
        idCard.setPlace("广州XXX");
        // 关系
        idCard.setPeople(people2);

        // ----保存----
        session.save(idCard);

        session.getTransaction().commit();
        session.close();

    }
}
