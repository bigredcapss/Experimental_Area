package JDBC;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Date;
import java.util.Properties;

/**
 * @Description:Driver,DriverManager,Connection,Statement,ResultSet的总结测试；JDBC的简单封装；
 * @author:BigRedCaps
 */
public class JDBCTestOne
{
    /**
     * title:①
     * Description:java.sql.Driver，sun公司提供的数据库驱动接口
     * @thorws SQLException
     */
    @Test
    public void DriverTest() throws SQLException
    {
        //mysql厂商对java.sql.Driver接口作实现
        Driver driver = new com.mysql.jdbc.Driver();

        //数据库连接的基本信息
        String url = "jdbc:mysql://localhost:3306/test";
        Properties info = new Properties();
        info.put("user","root");
        info.put("password","root");

        //获取数据库连接
        Connection conn = driver.connect(url,info);
        System.out.println(conn);


    }

    /**
     * title：②
     * Description:获取数据库连接并与具体数据库解耦
     * @throws Exception
     */
    @Test
    public void GetConnection_One() throws Exception
    {
        //连接数据库的必备参数
        String driverClass = null;
        String url = null;
        String user = null;
        String password = null;

        //读取mysqlinfo.properties文件
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("JDBC/DBinfo.properties");
        Properties properties = new Properties();
        properties.load(inputStream);
        driverClass = properties.getProperty("driver");
        url = properties.getProperty("url");
        user = properties.getProperty("user");
        password = properties.getProperty("password");

        //利用反射获取数据库驱动对象
        Driver driver = (Driver) Class.forName(driverClass).newInstance();
        Properties info = new Properties();
        info.put("user",user);
        info.put("password",password);

        Connection connection = driver.connect(url,info);
        System.out.println(connection);
    }

    /**
     * title:③
     * Description:java.sql.DriverManager 驱动管理类；可管理多个数据库驱动程序，根据驱动的不同，返回不同的数据库连接
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Test
    public void DriverManagerTest() throws SQLException, ClassNotFoundException
    {
        //连接数据库的必备参数
        String driverClass = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/test";
        String user = "root";
        String password = "root";

        //加载/注册驱动(对应的Driver 实现类通过静态代码块注册驱动【DriverManager.registerDriver(Class.forName(driverClass).newInstance())】)
        Class.forName(driverClass);

        //通过DriverManager的getConnection获取数据库连接
        Connection connection = DriverManager.getConnection(url,user,password);
        System.out.println(connection);
    }

    /**
     * title：④
     * Description:getConnection_One升级版
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    @Test
    public void getConnection_Two() throws IOException, ClassNotFoundException, SQLException
    {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("JDBC/DBinfo.properties");
        Properties properties = new Properties();
        properties.load(inputStream);

        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");

        Class.forName(driver);

        Connection connection = DriverManager.getConnection(url,user,password);
        System.out.println(connection);

    }

    /**
     * title:⑤
     * Description:java.sql.Statement:1)操作sql语句的对象；2）调用connection对象的createStatement()方法创建；
     * 3）调用executeUpdate()方法执行sql语句时，可传入insert,delete,update语句，不可传入select语句；
     * 4)Connection,Statement都属于应用程序和数据库服务器的资源，使用后要关闭；
     * @throws Exception
     */
    @Test
    public void StatementTest() throws Exception
    {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("JDBC/DBinfo.properties");
        Properties properties = new Properties();
        properties.load(inputStream);

        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");

        //加载驱动
        Class.forName(driver);

        //获取数据库连接
        Connection connection = DriverManager.getConnection(url,user,password);

        //获取操作sql语句的Statement对象
        Statement statement = connection.createStatement();

        //执行sql语句
        String sql = "insert into customers (name,email,birth) value('cheche','2465053774@qq.com','1996-06-07')";
        statement.executeUpdate(sql);

        //关闭资源
        statement.close();
        connection.close();
    }

    /**
     * 更新方法
     * @version:1.0
     * @param sql
     */
    public void update(String sql)
    {
        Connection connection = null;
        Statement statement = null;

        try
        {
            connection = JDBCTools.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (Exception e)
        {
            e.printStackTrace();
        }finally
        {
            JDBCTools.releaseSource(connection,statement,null);
        }

    }

    /**
     * title:⑥
     * Description：测试更新方法
     */
    @Test
    public void UpdateTest()
    {
        String sql1 = "insert into customers (name,email,birth) value ('qiuqiu','qiuqiu@gmail.com','2000-09-06')";
        String sql2 = "update customers set name = 'bilibili' where id = 2";
        String sql3 = "delete from customers where id = 3";

        update(sql2);
    }

    //ResultSet，

    /**
     * title:⑦
     * Description:java.sql.ResultSet,JDBC封装的查询结果集对象；1）调用statement对象的executeQuery()方法获取；
     * 2）resultSet对象就是一张数据表，指针指向表中第一条记录之前，可以调用next()检测下一行是否有效，若有效，返回true,
     * 指针下移，相当于Iterator对象的hasNext()方法和next()方法的结合；
     * 3）当指针指向某一条记录时，可以通过调用getXxx(index)或getXxx("columnName")获取每一列的值，
     * e.g:getInt(1),getString("name")；4)ResultSet对象也需要进行关闭；
     */
    @Test
    public void ResultSetTest()
    {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try
        {
            //获取连接
            connection = JDBCTools.getConnection();
            //创建Statement对象
            statement = connection.createStatement();
            //执行sql语句
            String sql = "select * from customers where id =2";
            resultSet = statement.executeQuery(sql);
            //获取结果集
            while(resultSet.next())
            {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String email = resultSet.getString(3);
                Date birth = resultSet.getDate(4);

                System.out.println(id);
                System.out.println(name);
                System.out.println(email);
                System.out.println(birth);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }finally
        {
            //关闭资源
            JDBCTools.releaseSource(connection,statement,resultSet);
        }
    }
}
