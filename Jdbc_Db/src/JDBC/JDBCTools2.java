package JDBC;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Description:使用C3p0连接池修改获取数据库连接
 * @Author:BigRedCaps
 */
public class JDBCTools2
{
    private static DataSource dataSource = null;
    //数据库连接池只被初始化一次
    static
    {
        dataSource = new ComboPooledDataSource("c3p0");
    }
    public static Connection getConnection() throws SQLException
    {
        return dataSource.getConnection();
    }
}
