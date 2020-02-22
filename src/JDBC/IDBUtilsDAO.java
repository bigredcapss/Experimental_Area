package JDBC;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @Description:使用DBUtils编写通用的Dao
 * @param: T：DAO处理的实体类的类型
 * @Author:BigRedCaps
 */
public interface IDBUtilsDAO<T>
{
    /**
     * 可以执行insert,update,delete操作
     * @param connection
     * @param sql
     * @param args
     */
    void update(Connection connection,String sql,Object...args);

    /**
     * 返回T的一条记录
     * @param connection
     * @param sql
     * @param args
     * @return
     */
    T get(Connection connection,String sql,Object...args) throws SQLException;

    /**
     * 返回T的一个集合
     * @param connection
     * @param sql
     * @param args
     * @return
     */
    List<T> getForList(Connection connection,String sql,Object...args);

    /**
     * 返回具体的一个值
     * @param connection
     * @param sql
     * @param args
     * @param <E>
     * @return
     */
    <E> E getForValue(Connection connection,String sql,Object...args);

    /**
     * 批量处理
     * @param connection
     * @param sql
     * @param args
     */
    void batch(Connection connection,String sql,Object[]...args);

}
