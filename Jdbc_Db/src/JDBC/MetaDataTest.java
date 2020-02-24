package JDBC;

import org.junit.Test;

import java.sql.*;

/**
 * @Description:JDBC元数据测试
 * @Author:BigRedCaps
 */
public class MetaDataTest
{
    /**
     * Description:DatabaseMetaData:描述数据库的元数据对象，用于获取数据库的整体综合信息；
     */
    @Test
    public void DatabaseMetaDataTest() throws SQLException
    {
        Connection connection = null;
        try
        {
            connection = JDBCTools.getConnection();
            DatabaseMetaData dataBaseMetaData = connection.getMetaData();

            //获取数据库的驱动名称
            String driverName = dataBaseMetaData.getDriverName();
            System.out.println(driverName);

            //获取数据库驱动的版本号
            String driverVersion = dataBaseMetaData.getDriverVersion();
            System.out.println(driverVersion);

            //获取数据库的版本号
            int databaseVersion = dataBaseMetaData.getDatabaseMajorVersion();
            System.out.println(databaseVersion);

            //获取Mysql中的数据库
            ResultSet resultSet = dataBaseMetaData.getCatalogs();
            while(resultSet.next())
            {
                System.out.println(resultSet.getString(1));
            }
            //...
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            JDBCTools.releaseSource(connection, null,null);
        }
    }

    /**
     * Description:ResultSetMetaData:1)描述结果集的元数据对象，用于获取关于 ResultSet 对象中列的类型和属性信息的对象；
     * 2)结合反射可以写出通用的查询方法
     */
    @Test
    public void ResultSetMetaDataTest()
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select * from customers";
        
        try
        {
            connection = JDBCTools.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            //获取结果集的列数
            int columnCount = resultSetMetaData.getColumnCount();
            System.out.println(columnCount);

            for (int i = 0; i < columnCount; i++)
            {
                //获取结果集列的名字
                String columnName = resultSetMetaData.getColumnName(i+1);
                //获取结果集列的别名
                String columnLabel = resultSetMetaData.getColumnLabel(i+1);
                System.out.println(columnName+"---"+columnLabel);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            JDBCTools.releaseSource(connection,preparedStatement,resultSet);
        }

    }
}
