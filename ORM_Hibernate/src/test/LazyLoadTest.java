package test;

import bean.manytoone.Dept;
import bean.manytoone.Employee;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

/**
 * @Description:懒加载测试
 * @Author:BigRedCaps
 */
public class LazyLoadTest
{
    private static SessionFactory sf;
    static
    {
        sf = new Configuration().configure().addClass(Dept.class).addClass(Employee.class).buildSessionFactory();
    }

    @Test
    public void get_load() {
        Session session = sf.openSession();
        session.beginTransaction();
        Dept dept = new Dept();
        // get： 及时查询
//		dept = (Dept) session.get(Dept.class, 9);
//		System.out.println(dept.getDeptName());

        // load，默认懒加载， 及在使用数据的时候，才向数据库发送查询的sql语句！
        dept = (Dept)session.load(Dept.class, 9);
        // 方式1： 先使用一下数据
        //dept.getDeptName();
        // 方式2：强迫代理对象初始化
        Hibernate.initialize(dept);
        // 方式3：关闭懒加载

        session.getTransaction().commit();
        session.close();

        // 关闭session之后，测试懒加载
        System.out.println(dept.getDeptName());
    }


    @Test
    public void set() {
        Session session = sf.openSession();
        session.beginTransaction();
        Dept dept = (Dept) session.get(Dept.class, 10);
        System.out.println(dept.getDeptName());
        System.out.println("------");
        System.out.println(dept.getEmps().isEmpty());

        session.getTransaction().commit();
        session.close();

    }
}
