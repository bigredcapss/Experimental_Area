package test;

import bean.Employee;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * @Description:Hibernate API应用测试
 * @Author:BigRedCaps
 */
public class HibernateTestOne
{
    /**
     * Hibernate API的第一个应用
     */
    @Test
    public void employeeTest()
    {
        // 对象
        Employee emp = new Employee();

        emp.setEmpName("班长");
        emp.setWorkDate(new Date());

        // 获取加载配置文件的管理类对象
        Configuration config = new Configuration();
        config.configure();  // 默认加载src/hibenrate.cfg.xml文件
        // 创建session的工厂对象
        SessionFactory sf = config.buildSessionFactory();
        // 创建session (代表一个会话，与数据库连接的会话)
        Session session = sf.openSession();
        // 开启事务
        Transaction tx = session.beginTransaction();
        //保存-数据库
        session.save(emp);
        // 提交事务
        tx.commit();
        // 关闭
        session.close();
        sf.close();
    }

    private static SessionFactory sf;
    static
    {
		/*
		//1. 创建配置管理类对象
		Configuration config = new Configuration();
		// 加载配置文件  (默认加载src/hibernate.cfg.xml)
		config.configure();
		//2. 根据加载的配置管理类对象，创建SessionFactory对象
		sf = config.buildSessionFactory();
		*/
        // 创建sf对象
        sf = new Configuration().configure().buildSessionFactory();
    }

    /**
     * 测试session的save方法
     * @throws Exception
     */
    @Test
    public void saveTest() throws Exception
    {
        // 对象
        Employee emp = new Employee();
        emp.setEmpName("李四");
        emp.setWorkDate(new Date());

        //根据session的工厂，创建session对象
        Session session = sf.openSession();
        // 开启事务
        Transaction tx = session.beginTransaction();
        //执行操作
        session.save(emp);
        // 提交事务
        tx.commit();
        //关闭session
        session.close();
    }

    /**
     * 测试session的saveOrUpdate方法
     * @throws Exception
     */
    @Test
    public void updateTest() throws Exception
    {
        // 对象
        Employee emp = new Employee();
        emp.setEmpId(10);
        emp.setEmpName("王五");
        // 创建session
        Session session = sf.openSession();
        //开启事务
        Transaction tx = session.beginTransaction();
        //执行操作
        // 若没有设置主键，则执行保存；若有设置主键，则执行更新操作; 若设置主键不存在则报错；
        session.saveOrUpdate(emp);
        //提交事务
        tx.commit();
        //关闭session
        session.close();
    }

    /**
     * 测试HQL查询
     * @throws Exception
     */
    @Test
    public void queryTest() throws Exception
    {

        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        //主键查询
        //Employee emp = (Employee) session.get(Employee.class, 1);
        //HQL查询
        Query q = session.createQuery("from Employee where empId=1 or empId=2");
        List<Employee> list = q.list();
        System.out.println(list);

        tx.commit();
        session.close();
    }


    /**
     * 测试QBC/Criteria查询(query by criteria)  完全面向对象的查询
     * @throws Exception
     */
    @Test
    public void qbcTest() throws Exception
    {
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        // QBC查询
        Criteria criteria = session.createCriteria(Employee.class);
        // 条件
        criteria.add(Restrictions.eq("empId", 1));
        List<Employee> list = criteria.list();
        System.out.println(list);

        tx.commit();
        session.close();
    }

    /**
     * 测试SQL查询
     * @throws Exception
     */
    @Test
    public void sqlTest() throws Exception
    {
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        //把每一行记录封装为对象数组，再添加到list集合
        //SQLQuery sqlQuery = session.createSQLQuery("select * from employee");
        //把每一行记录封装为指定的对象类型
        SQLQuery sqlQuery = session.createSQLQuery("select * from employee").addEntity(Employee.class);
        List list = sqlQuery.list();
        System.out.println(list);

        tx.commit();
        session.close();
    }

    /**
     * 测试自动建表
     * @throws Exception
     */
    @Test
    public void testCreate() throws Exception {
        // 创建配置管理类对象
        Configuration config = new Configuration();
        // 加载主配置文件
        config.configure();

        // 创建工具类对象
        SchemaExport export = new SchemaExport(config);
        // 建表
        // 第一个参数： 是否在控制台打印建表语句
        // 第二个参数： 是否执行脚本
        export.create(true, true);
    }

}
