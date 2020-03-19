package dao.impl;

import bean.manytoone.Employee;
import dao.IEmployeeDao;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:实现IEmployeeDao接口
 * @Author:BigRedCaps
 */
public class EmployeeDaoImpl implements IEmployeeDao
{
    @Override
    public void save (Employee emp)
    {
        Session session = null;
        Transaction tx = null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            //保存操作
            session.save(emp);
            tx.commit();
        } catch (HibernateException e)
        {
            e.printStackTrace();
        } finally
        {
            session.close();
        }
    }

    @Override
    public void update (Employee emp)
    {
        Session session = null;
        Transaction tx = null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            //更新操作
            session.update(emp);
            tx.commit();
        } catch (HibernateException e)
        {
            e.printStackTrace();
        } finally
        {
            session.close();
        }
    }

    @Override
    public Employee findById (Serializable id)
    {
        Session session = null;
        Transaction tx = null;
        Employee employee = null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            //主键查询
            employee = (Employee) session.get(Employee.class,id);
            tx.commit();
        } catch (HibernateException e)
        {
            e.printStackTrace();
        } finally
        {
            session.close();
        }
        return employee;
    }

    @Override
    public List<Employee> getAll()
    {
        Session session = null;
        Transaction tx = null;
        List<Employee> employees = null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            // HQL查询
            employees = session.createQuery("from Employee").list();
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            tx.commit();
            session.close();
        }
        return employees;
    }

    @Override
    public List<Employee> getAll(String employeeName)
    {
        Session session = null;
        Transaction tx = null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            Query q =session.createQuery("from Employee where empName=?");
            // 注意：参数索引从0开始
            q.setParameter(0, employeeName);
            // 执行查询
            return q.list();

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            tx.commit();
            session.close();
        }
    }

    @Override
    public List<Employee> getAll(int index, int count) {
        Session session = null;
        Transaction tx = null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            Query q = session.createQuery("from Employee");
            // 设置分页参数
            q.setFirstResult(index);  // 查询的起始行
            q.setMaxResults(count);	  // 查询返回的行数

            List<Employee> list = q.list();
            return list;
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        } finally
        {
            tx.commit();
            session.close();
        }
    }

    @Override
    public void delete (Serializable id)
    {
        Session session = null;
        Transaction tx = null;
        try
        {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            // 先根据id查询对象，再判断删除
            Object obj = session.get(Employee.class, id);
            if (obj != null) {
                session.delete(obj);
            }
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        } finally
        {
            tx.commit();
            session.close();
        }
    }
}
