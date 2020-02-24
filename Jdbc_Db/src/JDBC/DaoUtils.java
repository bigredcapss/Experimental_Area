package JDBC;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:重构Dao查询方法
 * @Author:BigRedCaps
 */
public class DaoUtils
{
    /**
     * Description:获取结果集的列名/列的别名 对应的集合
     * @param resultSet
     * @return
     * @throws SQLException
     */
    public static List<String> getColumnLabels(ResultSet resultSet) throws SQLException
    {
        List<String> labels = new ArrayList<>();
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        int columnCount = resultSetMetaData.getColumnCount();
        for (int i = 0; i < columnCount; i++)
        {
            labels.add(resultSetMetaData.getColumnLabel(i+1));
        }
        return labels;
    }

    /**
     * Description:处理结果集将结果集转为Map
     * @param resultSet
     * @param columnLabels
     * @return
     * @throws SQLException
     */
    public static List<Map<String, Object>> handleResultSetToMap (ResultSet resultSet, List<String> columnLabels)
            throws SQLException
    {
        List<Map<String,Object>> values = new ArrayList<>();
        Map<String,Object> map = null;
        //使用while循环处理ResultSet结果集对象
        while (resultSet.next())
        {
            map = new HashMap<>();
            //代码重构处
            for (String columnLabel:columnLabels)
            {
                Object columnValue = resultSet.getObject(columnLabel);
                map.put(columnLabel, columnValue);
            }
            //把一条记录的一个Map对象放到
            values.add(map);
        }
        return values;
    }

    /**
     * Description: 将一条记录转换为一个实体对象,进而将List中的多条记录转换为多个实体对象
     * @param clazz
     * @param values
     * @param <T>
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static  <T> List<T> transferMapListToBeanList (Class<T> clazz, List<Map<String, Object>> values)
            throws InstantiationException, IllegalAccessException, InvocationTargetException
    {
        //判断values集合是否为空集合，若不为空，遍历该集合，得到一个个的Map对象，再把一个个的Map对象转换为一个Class
        //参数对应的Object对象
        List<T> list = new ArrayList<>();
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
        return list;
    }
}
