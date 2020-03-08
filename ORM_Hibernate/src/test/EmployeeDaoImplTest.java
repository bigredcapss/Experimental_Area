package test;

import bean.Employee;
import dao.impl.EmployeeDaoImpl;
import org.junit.Test;

import java.util.Date;

/**
 * @Description:EmployeeDaoImple测试
 * @Author:BigRedCaps
 */
public class EmployeeDaoImplTest
{
    private EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
    @Test
    public void save ()
    {
        employeeDao.save(new Employee(4,"完给",new Date()));
    }

    @Test
    public void update ()
    {
        Employee emp = new Employee();
        emp.setEmpId(4);
        emp.setEmpName("jack");
        emp.setWorkDate(new Date());
        employeeDao.update(emp);
    }

    @Test
    public void findById ()
    {
        System.out.println(employeeDao.findById(1));
    }

    @Test
    public void getAll ()
    {
        System.out.println(employeeDao.getAll());
    }

    @Test
    public void getAll1 ()
    {
        System.out.println(employeeDao.getAll("Larry"));
    }

    @Test
    public void getAll2 ()
    {
        System.out.println(employeeDao.getAll(0,2));
    }

    @Test
    public void delete ()
    {
        employeeDao.delete(4);
    }
}
