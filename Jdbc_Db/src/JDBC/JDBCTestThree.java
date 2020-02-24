package JDBC;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.List;


/**
 * @Description:1）Dao测试；2）获取自动生成的主键；3）读写Blob类型的数据
 * @Author:BigRedCaps
 */
public class JDBCTestThree
{
    Dao dao = new Dao();

    @Test
    public void updateTest()
    {
        String sql = "insert into customers (name,email,birth) values (?,?,?)";
        dao.update(sql,"复兴","fuxing@gmail.com",new Date(new java.util.Date().getTime()));
    }

    @Test
    public void getTest()
    {
        String sql = "select * from customers where id = ?";
        Customers customers = dao.get(Customers.class,sql,6);
        System.out.println(customers);
    }

    @Test
    public void getForListTest()
    {
        String sql = "select flowId,type,idCard,examCard,studentName,location,grade from examstudent";
        List<Student> students = dao.getForList2(Student.class,sql);
        System.out.println(students);
    }

    @Test
    public void getForValueTest()
    {
        String sql = "select examCard from examstudent where flowId = ?";
        Object examCard = dao.getForValue(sql,3);
        System.out.println(examCard);

    }

    /**
     * Description:获取自动生成的主键，具体可查看Dao.md文档
     */
    @Test
    public void getGeneratedKey()
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "insert into customers (name,email,birth) values (?,?,?)";

        try
        {
            connection = JDBCTools.getConnection();
            //preparedStatement = connection.prepareStatement(sql);
            //通过重载的prepareStatement()方法预处理sql语句并自动返回主键值
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //设置占位符的值
            preparedStatement.setString(1,"阿冷");
            preparedStatement.setString(2,"aleng@gmail.com");
            preparedStatement.setDate(3,new Date(new java.util.Date().getTime()));

            preparedStatement.executeUpdate();
            //通过preparedStatement对象的getGeneratedKeys()获取包含了新增主键的ResultSet对象
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next())
            {
                System.out.println(resultSet.getObject(1));
            }

            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            ////通过打印可知，通过getGeneratedKeys()方法返回的resultSet对象中，只有一列，此列为GENERATED_KEY
            int columnCount = resultSetMetaData.getColumnCount();
            System.out.println(columnCount);
            for (int i = 0; i < resultSetMetaData.getColumnCount(); i++)
            {
                String columnName = resultSetMetaData.getColumnName(i+1);
                System.out.println(columnName);
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            JDBCTools.releaseSource(connection,preparedStatement,null);
        }

    }

    /**
     * Description：向数据库中插入Blob类型的数据
     */
    @Test
    public void InsertBlobTest()
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "insert into customers (name,email,birth,headIcon) values (?,?,?,?)";

        try
        {
            connection = JDBCTools.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            //设置占位符的值
            preparedStatement.setString(1,"阿热");
            preparedStatement.setString(2,"are@gmail.com");
            preparedStatement.setDate(3,new Date(new java.util.Date().getTime()));

            //插入Blob类型的数据
            InputStream inputStream = new FileInputStream("staticresouce/image/logo.png");
            preparedStatement.setBlob(4, inputStream);

            preparedStatement.executeUpdate();
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            JDBCTools.releaseSource(connection,preparedStatement,null);
        }
    }

    /**
     * Description:从数据库中读取Blob类型的数据
     */
    @Test
    public void ReadBlobTest()
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select id,name,email,birth,headIcon from customers where id = 9";

        try
        {
            connection = JDBCTools.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String email = resultSet.getString(3);
                java.util.Date birth = resultSet.getDate(4);
                System.out.println(id +","+ name + "," + email+ "," + birth);

                //从数据库中读取Blob类型的数据
                Blob headIcon = resultSet.getBlob(5);
                InputStream inputStream = headIcon.getBinaryStream();
                OutputStream outputStream = new FileOutputStream("staticresouce/image/llo.png");
                byte[] buffer = new byte[1024];
                int length = 0;
                while((length = inputStream.read(buffer))!=-1)
                {
                    outputStream.write(buffer,0,length);
                }
                outputStream.close();
                inputStream.close();
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
