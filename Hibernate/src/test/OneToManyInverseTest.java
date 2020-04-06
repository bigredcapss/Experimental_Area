package test;

import bean.manytoone.Dept;
import bean.manytoone.Employee2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

/**
 * @Description:Inverse属性测试
 * @Author:BigRedCaps
 */
public class OneToManyInverseTest
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
     * 测试inverse属性对保存数据的影响
     * inverse=true, 即控制反转，部门方没有控制权，部门和员工的数据
     * 都会保存；但是不会维护关联关系；
     * inverse=false,即控制不反转，部门方有控制权，部门和员工的数据
     * 都会保存；同时维护部门与员工的关联关系；
     */
    @Test
    public void saveTest()
    {
        Session session = sf.openSession();
        session.beginTransaction();

        // 部门对象
        Dept dept = new Dept();
        dept.setDeptName("应用开发部");
        // 员工对象
        Employee2 emp_zs = new Employee2();
        emp_zs.setEmpName("张三");
        Employee2 emp_ls = new Employee2();
        emp_ls.setEmpName("李四");
        // 维护关系
        dept.getEmps().add(emp_zs);
        dept.getEmps().add(emp_ls);

        // 保存操作--注意先保存多的一方，再保存一的一方
        session.save(emp_zs);
        session.save(emp_ls);
        session.save(dept); // 保存部门，部门下所有的员工

        session.getTransaction().commit();
        session.close();
    }

    /**
     * 测试inverse属性对获取数据的影响
     */
    @Test
    public void getTest()
    {
        Session session = sf.openSession();
        session.beginTransaction();

        Dept dept = (Dept) session.get(Dept.class, 1);
        System.out.println(dept.getDeptName());

        //org.hibernate.LazyInitializationException: illegal access to loading collection
        System.out.println(dept.getEmps());

        session.getTransaction().commit();
        session.close();
    }


    /**
     * 测试解除关联关系对数据表的影响
     * inverse=false，当前方（部门）有控制权，可以解除关联，结果是将员工表的外键字段设置为NULL；
     * inverse=true，当前方(部门)没有控制权，不能解除关联关系(不会生成update语句,也不会报错)；
     */
    @Test
    public void removeRelation()
    {
        Session session = sf.openSession();
        session.beginTransaction();

        // 获取部门
        Dept dept = (Dept) session.get(Dept.class, 2);

        // 解除关系
        dept.getEmps().clear();

        session.getTransaction().commit();
        session.close();
    }

    /**
     * 测试inverse属性对删除数据的影响
     * inverse=false, 有控制权，可以删除；先清空外键引用，再删除数据；
     * inverse=true,  没有控制权， 如果删除的记录有被外键引用，会报错，违反主外键引用约束；
     * 如果删除的记录没有被引用，可以直接删除;
     */
    @Test
    public void deleteTest()
    {
        Session session = sf.openSession();
        session.beginTransaction();

        // 查询部门
        Dept dept = (Dept) session.get(Dept.class, 7);
        session.delete(dept);

        session.getTransaction().commit();
        session.close();
    }

}
