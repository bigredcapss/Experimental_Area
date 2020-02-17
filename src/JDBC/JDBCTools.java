package JDBC;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @Description:JDBC工具类，简单封装了获取连接，释放资源,更新数据的方法
 * @Author:BigRedCaps
 */
public class JDBCTools
{
    /**
     * 获取数据库连接
     * @return Connection
     * @throws Exception
     */
    public static Connection getConnection() throws Exception
    {
        InputStream inputStream = JDBCTools.class.getClassLoader().getResourceAsStream("JDBC/DBinfo.properties");
        Properties properties = new Properties();
        properties.load(inputStream);

        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");

        Class.forName(driver);

        Connection connection = DriverManager.getConnection(url,user,password);
        return  connection;
    }

    /**
     * 释放资源
     * @param statement
     * @param connection
     * @param resultSet
     */
    public static void releaseSource(Connection connection, Statement statement, ResultSet resultSet)
    {
        if (connection!=null)
        {
            try
            {
                connection.close();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

        if(statement!=null)
        {
            try
            {
                statement.close();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

        if(resultSet!=null)
        {
            try
            {
                resultSet.close();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * 更新方法
     * @version: 1.0
     * @param sql
     */
    public static void update(String sql)
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
}
