package JDBC;

import org.apache.commons.beanutils.BeanUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:DAO（访问数据信息类）
 * @Author:BigRedCaps
 */
public class Dao
{
    /**
     * Description:该方法可执行Insert,Delete,Update操作
     * @param sql
     * @param args
     */
    void update(String sql,Object...args)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try
        {
            connection = JDBCTools.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++)
            {
                preparedStatement.setObject(i+1,args[i]);
            }
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
     * Description:该方法可执行查询一条记录的操作，返回对应的对象
     * @param clazz
     * @param sql
     * @param args
     * @param <T>
     * @return
     */
    <T> T get(Class<T> clazz,String sql,Object...args)
    {
        T entity = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try
        {
            connection = JDBCTools.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++)
            {
                preparedStatement.setObject(i+1,args[i]);
            }
            resultSet = preparedStatement.executeQuery();
            
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            
            if(resultSet.next())
            {
                Map<String,Object> values = new HashMap<>();

                int columnCount = resultSetMetaData.getColumnCount();
                for (int i = 0; i < columnCount; i++)
                {
                    String columnLabel = resultSetMetaData.getColumnLabel(i+1);
                    Object columnValue = resultSet.getObject(i+1);
                    values.put(columnLabel,columnValue);
                }

                entity =clazz.newInstance();
                if(values.size()>0)
                {
                    for (Map.Entry<String, Object> entry : values.entrySet())
                    {
                        String propertyName = entry.getKey();
                        Object propertyValue = entry.getValue();
                        //ReflectUtils.setFieldValue(entity,propertyName,propertyValue);
                        BeanUtils.setProperty(entity,propertyName,propertyValue);
                    }   
                }
            }
            
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            JDBCTools.releaseSource(connection,preparedStatement,resultSet);
        }
        return entity;
    }

    /**
     * Description:该方法可执行查询多条记录的操纵，返回对应对象的集合
     * @param clazz
     * @param sql
     * @param args
     * @param <T>
     * @return
     */
    <T> List<T> getForList(Class<T> clazz, String sql, Object...args)
    {
        List<T> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try
        {
            connection = JDBCTools.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++)
            {
                preparedStatement.setObject(i+1,args[i]);
            }
            resultSet = preparedStatement.executeQuery();

            List<Map<String,Object>> values = new ArrayList<>();
            Map<String,Object> map = null;

            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            //使用while循环处理ResultSet结果集对象
            while (resultSet.next())
            {
                map = new HashMap<>();

                int columnCount = resultSetMetaData.getColumnCount();
                for (int i = 0; i < columnCount; i++)
                {
                    String columnLabel = resultSetMetaData.getColumnLabel(i + 1);
                    Object columnValue = resultSet.getObject(columnLabel);
                    map.put(columnLabel, columnValue);
                }
                //把一条记录的一个Map对象放到
                values.add(map);
            }
            //判断values集合是否为空集合，若不为空，遍历该集合，得到一个个的Map对象，再把一个个的Map对象转换为一个Class
            //参数对应的Object对象
            T entity = null;
            if(values.size()>0)
            {
                for (Map<String, Object> m : values)
                {
                    entity =clazz.newInstance();
                    for (Map.Entry<String, Object> entry : m.entrySet())
                    {
                        String propertyName = entry.getKey();
                        Object propertyValue = entry.getValue();

                        BeanUtils.setProperty(entity,propertyName,propertyValue);
                    }
                    list.add(entity);
                }
            }
            System.out.println(list);
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            JDBCTools.releaseSource(connection,preparedStatement,resultSet);
        }
        return list;
    }


    /**
     * Description:重构getForList方法
     * @version:2.0
     * @param clazz
     * @param sql
     * @param args
     * @param <T>
     * @return
     */
    <T> List<T> getForList2(Class<T> clazz, String sql, Object...args)
    {
        List<T> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try
        {
            connection = JDBCTools.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++)
            {
                preparedStatement.setObject(i+1,args[i]);
            }
            //1.获取结果集
            resultSet = preparedStatement.executeQuery();

            //代码重构处
            //2.处理结果集，得到List集合包裹着的Map集合，其中一个Map对象就是一条记录
            List<String> columnLabels = DaoUtils.getColumnLabels(resultSet);
            //3.把Map对应的List转为clazz对应的List
            List<Map<String, Object>> values = DaoUtils.handleResultSetToMap(resultSet, columnLabels);
            list = DaoUtils.transferMapListToBeanList(clazz,values);

        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            JDBCTools.releaseSource(connection,preparedStatement,resultSet);
        }
        return list;
    }

    /**
     * 该方法可执行返回某条记录的某一个字段的值或一个统计的值的操作
     * @param sql
     * @param args
     * @param <E>
     * @return
     */
    <E> E getForValue(String sql,Object...args)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try
        {
            connection = JDBCTools.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++)
            {
                preparedStatement.setObject(i+1,args[i]);
            }
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
            {
                return (E) resultSet.getObject(1);
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }finally
        {
            JDBCTools.releaseSource(connection,preparedStatement,resultSet);
        }
        return null;
    }
}
