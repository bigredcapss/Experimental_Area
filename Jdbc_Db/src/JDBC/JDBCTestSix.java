package JDBC;

import org.apache.commons.dbutils.QueryLoader;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.*;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description:DBUtils框架：QueryRunner,ResultSetHandler接口，以及几个重要的Handler
 * @Author:BigRedCaps
 */
public class JDBCTestSix
{
    //使用可插拔策略执行SQL查询以处理ResultSet,此类是线程安全的。
    QueryRunner queryRunner = new QueryRunner();

    /**
     * 测试QueryRunner类，update方法可执行insert,update,delete操作;可以研究一下dbutils的源码
     */
    @Test
    public void queryRunnerWithUpdateTest()
    {
        String sql = "delete from customers where id in (?,?)";
        //String sql = "update customers set name = ? where id = ?";
        Connection connection = null;

        try
        {
            connection = JDBCTools.getConnection();
            //使用QueryRunner对象的update方法执行sql
            queryRunner.update(connection,sql,7,8);
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            JDBCTools.releaseSource(connection,null,null);
        }

    }

    /**
     * 测试QueryRunner类，query(Connection connection,String sql,ResultSetHandler<T> rsh)；
     * QueryRunner类的query方法的返回值取决于ResultSetHandler参数的handler方法的返回值；
     */
    @Test
    public void queryRunnerWithQueryTest()
    {
        Connection connection = null;
        String sql = "select * from customers";

        try
        {
            connection = JDBCTools.getConnection();
            Object object = queryRunner.query(connection,sql,new MyResultSetHandler2());
            System.out.println(object);
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            JDBCTools.releaseSource(connection,null,null);
        }

    }

    /**
     * 内部类：自定义结果集处理器，实现ResultHandler接口；其原理实质上是用的采用动态代理实现
     * MyResultSetHandler1简单测试；
     */
    class MyResultSetHandler1 implements ResultSetHandler
    {

        //handler的返回值作为了query的返回值
        @Override
        public Object handle (ResultSet resultSet) throws SQLException
        {
            System.out.println("自定义结果集处理器1");
            return "wangguoxu";
        }
    }

    /**
     * MyResultHandler2将结果集封装为对象
     */
    class MyResultSetHandler2 implements ResultSetHandler
    {

        List<Customers> customers = new ArrayList<>();

        //handler的返回值作为了query的返回值
        @Override
        public Object handle (ResultSet resultSet) throws SQLException
        {
            while(resultSet.next())
            {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String email = resultSet.getString(3);
                Date birth = resultSet.getDate(4);
                Customers customer = new Customers(id,name,email,birth);
                customers.add(customer);
            }
            return customers;
        }
    }

    /**
     * BeanHandler测试：把结果集的第一条记录转为创建BeanHandler对象时传入的的Class参数对应的对象
     */
    @Test
    public void beanHandlerTest()
    {
        Connection connection = null;

        try
        {
            connection = JDBCTools.getConnection();
            String sql = "select id,name,email,birth from customers where id >= ?";
            Customers customers = queryRunner.query(connection,sql,new BeanHandler<>(Customers.class),4);
            System.out.println(customers);
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            JDBCTools.releaseSource(connection,null,null);
        }
    }

    /**
     * BeanListHandler测试：把结果集转为一个List,该List不为null,但可能为空集合(size()方法返回0)；
     * 若sql语句能够查询到记录，List中存放创建BeanListHandler对象时传入的的Class参数对应的对象；
     */
    @Test
    public void beanListHandlerTest()
    {
        Connection connection = null;

        try
        {
            connection = JDBCTools.getConnection();
            String sql = "select id,name,email,birth from customers";
            List<Customers> customers =(List<Customers>) queryRunner.query(connection,sql,
                    new BeanListHandler<>(Customers.class));
            System.out.println(customers);
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            JDBCTools.releaseSource(connection,null,null);
        }
    }

    /**
     * MapHandler测试：返回sql语句对应的第一条记录对应的Map对象；
     * 键：sql查询的列名（不是列的别名），值：sql查询的列值；
     * 这里也可以通过别名建立映射关系；
     */
    @Test
    public void mapHandlerTest()
    {
        Connection connection = null;

        try
        {
            connection = JDBCTools.getConnection();
            String sql = "select id,name,email,birth from customers";
            Map<String,Object> result = queryRunner.query(connection,sql,new MapHandler());
            System.out.println(result);
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            JDBCTools.releaseSource(connection,null,null);
        }
    }

    /**
     * MapListHandler测试：将结果集转为一个Map的List；
     * 每个Map对应查询的一条记录：键：sql查询的列名（不是列的别名），值：sql查询的列值；
     * 而MapListHandler:返回多条记录对应的Map的集合；
     */
    @Test
    public void mapListHandlerTest()
    {
        Connection connection = null;

        try
        {
            connection = JDBCTools.getConnection();
            String sql = "select id,name,email,birth from customers";
            List<Map<String,Object>> result = queryRunner.query(connection,sql,new MapListHandler());
            System.out.println(result);
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            JDBCTools.releaseSource(connection,null,null);
        }
    }

    /**
     * ScalarHandler测试：把结果集转为一个值（可以是任意基本数据类型或者String,Date等类型）；
     */
    @Test
    public void ScalarHandlerTest()
    {
        Connection connection = null;

        try
        {
            connection = JDBCTools.getConnection();
            String sql = "select count(*) from customers";
            Object result = queryRunner.query(connection,sql,new ScalarHandler());
            System.out.println(result);
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            JDBCTools.releaseSource(connection,null,null);
        }
    }

    /**
     * QueryLoader:可以用来加载存放着sql语句的资源文件；
     * 使用该类可以把sql语句外置化到一个资源文件中，以提供更好的解耦；
     */
    @Test
    public void queryLoaderTest() throws IOException
    {
        //代表类路径的根目录
        Map<String,String> sqls = QueryLoader.instance().load("");
        String updateSql = sqls.get("UPDATE_CUSTOMERS");
        System.out.println(updateSql);
    }





}
