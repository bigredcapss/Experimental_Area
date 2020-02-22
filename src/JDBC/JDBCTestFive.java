package JDBC;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.junit.Test;

import javax.sound.midi.Soundbank;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Description:数据库连接池
 * @Author:BigRedCaps
 */
public class JDBCTestFive
{

    /**
     * DBCP数据源基本测试
     * @throws SQLException
     */
    @Test
    public void dbcpPoolTest_One() throws SQLException
    {
        BasicDataSource dataSource = null;
        //创建dbcp数据源实例
        dataSource = new BasicDataSource();
        //为数据源实例指定必须的属性
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setUrl("jdbc:mysql:///test");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        //从数据源中获取数据库连接
        Connection connection = dataSource.getConnection();
        System.out.println(connection.getClass());

    }

    /**
     * DBCP数据源实例设定额外的属性测试
     * @throws SQLException
     */
    @Test
    public void dbcpPoolTest_Two() throws SQLException
    {
        //创建数据源实例
        BasicDataSource dataSource = new BasicDataSource();
        //为数据源实例指定必须的属性
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setUrl("jdbc:mysql:///test");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        //为数据源实例指定额外的属性
        dataSource.setInitialSize(10);
        dataSource.setMaxActive(50);
        //从数据源中获取数据库连接
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

    /**
     * DBCP数据源工厂加载properties配置文件测试
     * 注意：1）使用BasicDataSourceFactory加载properties配置文件，配置文件的键需要来自BasicDataSource的属性；
     * 2）通过BasicDataSourceFactory的createDataSource()方法加载配置文件创建数据源实例；
     * @throws Exception
     */
    @Test
    public void dbcpWithDataSourceFactoryTest() throws Exception
    {
        Properties properties = new Properties();
        InputStream inputStream = JDBCTestFive.class.getClassLoader().getResourceAsStream("JDBC/dbcp.properties");
        properties.load(inputStream);

        DataSource dataSource = BasicDataSourceFactory.createDataSource(properties);
        System.out.println(dataSource.getConnection());

        BasicDataSource basicDataSource = (BasicDataSource) dataSource;
        System.out.println(basicDataSource.getMaxActive());
    }

    /**
     * 测试dbcp连接池的最长等待时间；
     * 思路：设置连接池最长等待时间为5s,事先获得5个连接，然后用一个线程继续获取连接，此次该请求排队；用另一个线程，让主
     * 线程睡眠，从而释放连接；若报异常超时，则验证成功；
     * @throws SQLException
     */
    @Test
    public void dbcpVertifyMaxWaiteTimeTest() throws SQLException
    {
        //创建数据源实例
        BasicDataSource dataSource = new BasicDataSource();
        //为数据源实例指定必须的属性
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setUrl("jdbc:mysql:///test");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        //为数据源实例指定额外的属性
        dataSource.setInitialSize(5);//初始化连接数
        dataSource.setMaxActive(5);//最大连接数
        dataSource.setMinIdle(2);//最小空闲连接数
        dataSource.setMaxWait(5000);//最长等待时间
        //从数据源中获取数据库连接
        Connection connection = dataSource.getConnection();
        System.out.println(connection);

        connection = dataSource.getConnection();
        System.out.println(connection);

        connection = dataSource.getConnection();
        System.out.println(connection);

        connection = dataSource.getConnection();
        System.out.println(connection);

        Connection connection2 = dataSource.getConnection();
        System.out.println("第五个连接"+connection);

        /**
         * 开启一个线程，继续向连接池申请连接
         */
        new Thread()
        {
            public void run()
            {
                try
                {
                    Connection connection = dataSource.getConnection();
                    System.out.println(connection.getClass());
                } catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }.start();

        //主线程睡眠3s,释放连接
        try
        {
            Thread.sleep(5500);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        connection2.close();
    }

    /**
     * c3p0数据源测试
     * @throws Exception
     */
    @Test
    public void c3p0PoolTest() throws Exception
    {
        //创建数据源实例
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();

        //为数据源实例指定必须的属性
        comboPooledDataSource.setDriverClass("com.mysql.jdbc.Driver");
        comboPooledDataSource.setJdbcUrl("jdbc:mysql:///test");
        comboPooledDataSource.setUser("root");
        comboPooledDataSource.setPassword("root");

        //从c3p0数据源中获取数据库连接
        Connection connection = comboPooledDataSource.getConnection();
        System.out.println(connection);
    }


    /**
     * 读取c3p0-config.xml配置文件，获取连接池属性
     * @throws SQLException
     */
    @Test
    public void c3p0PoolWithConfigTest() throws SQLException
    {
        //创建数据源实例
        //注意:该配置文件只能被放在src目录下
        DataSource dataSource = new ComboPooledDataSource("c3p0");
        System.out.println(dataSource.getConnection());

        ComboPooledDataSource comboPooledDataSource = (ComboPooledDataSource) dataSource;
        System.out.println(comboPooledDataSource.getMaxStatements());
        System.out.println(comboPooledDataSource.getAcquireIncrement());
    }



}
