package JDBC;

import org.junit.Test;

import java.sql.Connection;

/**
 * @Description:编码的过程：IDBUtilsDAO-->DBUtilsDAO-->CustomersDao-->CustomersDaoTest;
 * @Author:BigRedCaps
 */
public class CutomersDaoTest
{
    CustomersDao customersDao = new CustomersDao();

    @Test
    public void DBUtilsDAOGetTest()
    {
        Connection connection = null;

        try
        {
            connection = JDBCTools.getConnection();
            String sql = "select * from customers where id = ?";
            Customers customers = customersDao.get(connection,sql,3);
            System.out.println(customers);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
