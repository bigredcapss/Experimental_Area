package JDBC;

import org.junit.Test;

import java.sql.*;

/**
 * @Description:数据库的事务、批量处理
 * @Author:BigRedCaps
 */
public class JDBCTestFour
{
    Dao dao = new Dao();

    /**
     * Decription：Jerry给建国转50元；1）由于异常，sql1执行成功，但sql2未执行，导致转账失败；2）问题在于该转账操作未在
     * 一个事务中执行；3)如果多个操作，每个操作使用的是自己的单独的连接，则无法保证事务
     */
    @Test
    public void TransactionTest1()
    {
        String sql1 = "update users set balance = balance - 50 where id = 1";
        dao.update(sql1);

        int i = 10/0;
        System.out.println(i);

        String sql2 = "update users set balance = balance + 50 where id = 2";
        dao.update(sql2);
    }

    /**
     * Description：1）确保一个Connection对象对应一个事务，设置事务手动提交；2）遇到异常可以回滚事务；
     */
    @Test
    public void TransactionTest2()
    {
        Connection  connection = null;

        try
        {
            connection = JDBCTools.getConnection();

            //开始事务：取消默认提交
            connection.setAutoCommit(false);

            String sql = "update users set balance = balance - 50 where id = 1";
            update(connection,sql);

            int i = 10/0;
            System.out.println(i);

            sql = "update users set balance = balance + 50 where id = 2";
            update(connection,sql);

            //提交事务
            connection.commit();
        } catch (Exception e)
        {
            e.printStackTrace();

            try
            {
                //回滚事务
                connection.rollback();
            } catch (SQLException e1)
            {
                e1.printStackTrace();
            } finally
            {
                JDBCTools.releaseSource(connection,null,null);
            }

        }
    }

    /**
     * Description:为看到事务的详细情况，而专门写的update()方法;这里Connection连接对象是传进来的参数，因此关闭connection
     * 对象无须在这里关闭；
     * @param connection
     * @param sql
     * @param args
     */
    public void update(Connection connection,String sql,Object...args)
    {
        PreparedStatement preparedStatement = null;

        try
        {
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
            JDBCTools.releaseSource(null,preparedStatement,null);
        }
    }

    /**
     * Description:为看到事务隔离级别的详细情况，而专门写的getForValue()方法;
     * @param sql
     * @param args
     * @param <E>
     * @return
     */
    public <E> E getForValue(String sql,Object...args)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try
        {
            connection = JDBCTools.getConnection();

            //设置事务的隔离级别
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

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

    /**
     * Description:造成出现脏读现象
     * 使用单元测试开启两个线程，一个往数据库写数据（但未提交），一个从数据库中读数据；写数据线程可以在Debug模式下暂停一
     * 下提交事务，让读数据线程去读（同时设置事务的隔离级别为Read Uncommited[读未提交]）
     */
    @Test
    public void TransactionIsolationWriteTest()
    {
        Connection connection = null;

        try
        {
            connection = JDBCTools.getConnection();
            connection.setAutoCommit(false);

            String sql = "update users set balance = balance -500 where id =1";
            update(connection,sql);

            connection.commit();
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            JDBCTools.releaseSource(connection,null,null);
        }
    }

    /**
     * Description：读数据线程
     */
    @Test
    public void TransactionIsolationReadTest()
    {
        String sql = "select balance from users where id = 1";
        Integer balance = getForValue(sql);
        System.out.println(balance);
    }


    /**
     * Description:使用Statement对象向Oracle中插入100000条记录测试
     * DBinfo.properties文件中使用oracle的配置
     */
    @Test
    public void insertBatchWithStatementTest()
    {
        Connection connection = null;
        Statement statement = null;

        try
        {
            connection = JDBCTools.getConnection();

            //开启事务
            JDBCTools.beginTransaction(connection);

            statement = connection.createStatement();

            //定义开始时间
            long beginTime = System.currentTimeMillis();

            for (int i = 0; i < 100000; i++)
            {
                String sql = "insert into customers values ("+i+1+",'name-"+i+"','2460"+i+"@gmail.com')";
                statement.executeUpdate(sql);
            }

            //定义结束时间
            long endTime = System.currentTimeMillis();

            //插入10万条记录花费的时间25357ms
            System.out.println("Time:"+(endTime-beginTime));

            //提交事务
            JDBCTools.commit(connection);
        } catch (Exception e)
        {
            e.printStackTrace();
            //回滚事务
            JDBCTools.rollback(connection);
        } finally
        {
            JDBCTools.releaseSource(connection,statement,null);
        }
    }


    /**
     * Description:使用PreparedStatement对象向Oracle中插入100000条记录测试
     */
    @Test
    public void insertBatchWithPreparedStatementTest()
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = null;

        try
        {
            connection = JDBCTools.getConnection();

            //开启事务
            JDBCTools.beginTransaction(connection);

            sql = "insert into customers values(?,?,?)";

            preparedStatement = connection.prepareStatement(sql);

            //定义开始时间
            long beginTime = System.currentTimeMillis();
            for (int i = 0; i < 100000; i++)
            {
                preparedStatement.setInt(1,i+1);
                preparedStatement.setString(2,"name-"+i);
                preparedStatement.setString(3,"24650"+i+"@gmail.com");
                preparedStatement.executeUpdate();
            }
            //定义结束时间
            long endTime = System.currentTimeMillis();

            //向Oracle数据库插入100000条记录共花费5178ms
            System.out.println("Time:"+(endTime-beginTime));

            //提交事务
            JDBCTools.commit(connection);

        } catch (Exception e)
        {
            e.printStackTrace();
            //回滚事务
            JDBCTools.rollback(connection);
        } finally
        {
            JDBCTools.releaseSource(connection,preparedStatement,null);
        }
    }

    /**
     * Description:使用PreparedStatement对象的addBatch(),executeBatch(),clearBatch()方法,通过积攒sql语句
     * 向Oracle中插入100000条记录测试
     */
    @Test
    public void insertBatchWithPreparedStatementMutiplyTest()
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = null;

        try
        {
            connection = JDBCTools.getConnection();
            //开启事务
            JDBCTools.beginTransaction(connection);
            sql = "insert into customers values (?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            //定义开始时间
            long beginTime = System.currentTimeMillis();
            for (int i = 0; i < 100000; i++)
            {
                preparedStatement.setInt(1,i+1);
                preparedStatement.setString(2,"name-"+i);
                preparedStatement.setString(3,"24650"+i+"@gmail.com");

                //积攒sql语句
                preparedStatement.addBatch();
                //当积攒到一定程度时，就统一的执行一次，并清空当前积攒的sql
                if((i+1)%300==0)
                {
                    preparedStatement.executeBatch();
                    preparedStatement.clearBatch();
                }
            }
            //若sql语句的总条数不是批量数值的整数倍，则还需要在额外执行一次
            if(100000%300!=0)
            {
                preparedStatement.executeBatch();
                preparedStatement.clearBatch();
            }

            long endTime = System.currentTimeMillis();
            //插入100000条记录共花费的时间342ms
            System.out.println("Time:"+(endTime-beginTime));

            //提交事务
            JDBCTools.commit(connection);
        } catch (Exception e)
        {
            e.printStackTrace();
            //回滚事务
            JDBCTools.rollback(connection);
        } finally
        {
            JDBCTools.releaseSource(connection,preparedStatement,null);
        }
    }



}
