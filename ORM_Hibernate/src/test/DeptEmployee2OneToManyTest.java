package test;

import bean.Dept;
import bean.Employee2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

/**
 * @Description:部门与员工的一对多或员工与部门的多对一测试
 * @Author:BigRedCaps
 */
public class DeptEmployee2OneToManyTest
{
    private static SessionFactory sf;
    static
    {
        sf = new Configuration().configure()
                .addClass(Dept.class).addClass(Employee2.class)   // 测试时候使用
                .buildSessionFactory();
    }

    /**
     * 保存操作--通过保存部门从而保存员工（通过一的一方维护多的一方）
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

        // 关系维护
        dept.getEmps().add(emp_zs);
        dept.getEmps().add(emp_ls);

        // 保存操作--注意先保存多的一方，再保存一的一方
        session.save(emp_zs);
        session.save(emp_ls);
        session.save(dept); // 保存部门，部门下所有的员工

        session.getTransaction().commit();
        session.close();

        /*
        结果显示：
        Hibernate: insert into t_employee (empName, salary, dept_id) values (?, ?, ?)
        Hibernate: insert into t_employee (empName, salary, dept_id) values (?, ?, ?)
        Hibernate: insert into t_dept (deptName) values (?)
        Hibernate: update t_employee set dept_id=? where empId=?
        Hibernate: update t_employee set dept_id=? where empId=?
        */
    }

    /**
     * 保存操作--通过保存员工从而保存部门（通过多的一方维护一的一方）【推荐】
     */
    @Test
    public void saveTest2()
    {

        Session session = sf.openSession();
        session.beginTransaction();

        // 部门对象
        Dept dept = new Dept();
        dept.setDeptName("综合部");

        // 员工对象
        Employee2 emp_zs = new Employee2();
        emp_zs.setEmpName("张三");
        Employee2 emp_ls = new Employee2();
        emp_ls.setEmpName("李四");

        // 关系维护
        emp_zs.setDept(dept);
        emp_ls.setDept(dept);


        // 保存操作--注意先保存一的一方，再保存多的一方
        session.save(dept);
        session.save(emp_zs);//保存员工信息，从而保存该员工所属的部门信息
        session.save(emp_ls);

        session.getTransaction().commit();
        session.close();

       /*
        结果显示：
        Hibernate: insert into t_dept (deptName) values (?)
        Hibernate: insert into t_employee (empName, salary, dept_id) values (?, ?, ?)
        Hibernate: insert into t_employee (empName, salary, dept_id) values (?, ?, ?)
        少生成2条update  sql
        */

    }

    /**
     * 查询操作
     */
    @Test
    public void getTest()
    {
        Session session = sf.openSession();
        session.beginTransaction();

        // 通过部门方，获取另外一方
//        Dept dept = (Dept) session.get(Dept.class, 1);
//        System.out.println(dept.getDeptName());
//        System.out.println(dept.getEmps());// 懒加载

        // 通过员工方，获取另外一方
        Employee2 emp = (Employee2) session.get(Employee2.class, 1);
        System.out.println(emp.getEmpName());
        System.out.println(emp.getDept().getDeptName());

        session.getTransaction().commit();
        session.close();
    }
}
