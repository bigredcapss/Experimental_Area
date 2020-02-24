package JDBC;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @Description:使用DBUtils编写通用的Dao；实现IDBUtilDAO接口
 * @Author:BigRedCaps
 */
public class DBUtilsDAO<T> implements IDBUtilsDAO<T>
{
    private QueryRunner queryRunner = null;
    private Class<T> type;

    public DBUtilsDAO ()
    {
        queryRunner = new QueryRunner();
        type = ReflectUtils.getSuperGenericType(getClass());
    }

    @Override
    public void update (Connection connection, String sql, Object... args)
    {

    }

    @Override
    public T get (Connection connection, String sql, Object... args) throws SQLException
    {
        return queryRunner.query(connection,sql,new BeanHandler<>(type),args);
    }

    @Override
    public List getForList (Connection connection, String sql, Object... args)
    {
        return null;
    }

    @Override
    public void batch (Connection connection, String sql, Object[]... args)
    {

    }

    @Override
    public Object getForValue (Connection connection, String sql, Object... args)
    {
        return null;
    }
}
