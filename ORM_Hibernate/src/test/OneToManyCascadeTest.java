package test;

import bean.manytoone.Dept;
import bean.manytoone.Employee2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

/**
 * @Description:cascade级联操作测试
 * @Author:BigRedCaps
 */
public class OneToManyCascadeTest
{
    private static SessionFactory sf;
    static
    {
        sf = new Configuration()
                .configure()
                .addClass(Dept.class)
                .addClass(Employee2.class)
                .buildSessionFactory();
    }

    /**
     *  级联保存测试
     */
    @Test
    public void saveTest()
    {
        Session session = sf.openSession();
        session.beginTransaction();

        // 部门对象
        Dept dept = new Dept();
        dept.setDeptName("财务部");
        // 员工对象
        Employee2 emp_zs = new Employee2();
        emp_zs.setEmpName("张三");
        Employee2 emp_ls = new Employee2();
        emp_ls.setEmpName("李四");
        // 关系维护
        dept.getEmps().add(emp_zs);
        dept.getEmps().add(emp_ls);

        // 保存操作
        session.save(dept); // 需要设置级联保存； 保存部门，部门下所有的员工

        session.getTransaction().commit();
        session.close();
    }

    /**
     * 级联删除测试
     */
    @Test
    public void deleteTest()
    {
        Session session = sf.openSession();
        session.beginTransaction();

        Dept dept = (Dept) session.get(Dept.class,7);

        //org.hibernate.LazyInitializationException: illegal access to loading collection
        session.delete(dept); // 级联删除

        session.getTransaction().commit();
        session.close();
    }
}
