package test;

import bean.onetoone.IdCard;
import bean.onetoone.People;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

/**
 * @Description:身份证与人一对一映射
 * @Author:BigRedCaps
 */
public class PeopleIdCardOneToOneTest
{
    private static SessionFactory sf;
    static
    {
        sf = new Configuration().configure().addClass(IdCard.class).addClass(People.class).buildSessionFactory();
    }

    /**
     * 一对一的保存测试--基于外键的映射
     */
    @Test
    public void saveTest() {
        Session session = sf.openSession();
        session.beginTransaction();

        // 人对象
        People people = new People();
        people.setPeopleName("Jack");
        // 身份证对象
        IdCard idCard = new IdCard();
        idCard.setCardNum("441202XXX");
        idCard.setPlace("广州XXX");
        // 关系维护
        idCard.setPeople(people);

        // 保存操作
        session.save(idCard);

        session.getTransaction().commit();
        session.close();

    }


}
