package JDBC;



import org.junit.Test;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Description:
 * @Author:BigRedCaps
 */
public class JDBCTestTwo
{
    /**
     * title:①
     * Description:以面向对象的方式插入学生记录测试
     */
    @Test
    public void InsertStudentTest()
    {
        Student student = getStudentFromConsole();
        //InsertStudent(student);
        InsertStudent2(student);
    }

    /**
     * Description:使用Statement对象将student对象插入数据表中
     * @version: 1.0
     * @param student
     */
    public void InsertStudent(Student student)
    {
        //拼接sql语句
        String sql = "insert into examStudent values("+student.getFlowId()+","+student.getType()+"," +
                "'"+student.getIdCard()+"','"+student.getExamCard()+"','"+student.getStudentName()+"'," +
                "'"+student.getLocation()+"',"+student.getGrade()+")";
        //调用JDBCTools类的update方法
        JDBCTools.update(sql);
    }

    /**
     * Description:获取控制台输入参数，封装成student对象
     * @return Student
     */
    public Student getStudentFromConsole()
    {
        Scanner scanner = new Scanner(System.in);
        Student student = new Student();

        System.out.print("Please Input flowId:");
        student.setFlowId(scanner.nextInt());

        System.out.print("Please Input type:");
        student.setType(scanner.nextInt());

        System.out.print("Please Input idCard:");
        student.setIdCard(scanner.next());

        System.out.print("Please Input examCard:");
        student.setExamCard(scanner.next());

        System.out.print("Please Input studentName:");
        student.setStudentName(scanner.next());

        System.out.print("Please Input location:");
        student.setLocation(scanner.next());

        System.out.print("Please Input grade:");
        student.setGrade(scanner.nextInt());

        return student;
    }

    /**
     * itle:②
     * Description:根据学生的准考证号/身份证号，查询学生的四六级成绩
     */
    @Test
    public void getStudentTest()
    {
        //获取查询类型（四级/六级）
        int serchType = getSerchTypeFromConsole();

        //具体查询学生信息
        Student student = serchStudent(serchType);

        //打印学生信息
        printStudent(student);
    }

    /**
     * Description:获取查询类型；1代表按身份证号查询；2代表按准考证号查询；
     * @return searchType
     */
    private int getSerchTypeFromConsole ()
    {
        System.out.print("请输入查询类型：（注：1代表按身份证号查询；2代表按准考证号查询；）");
        Scanner scanner = new Scanner(System.in);
        int searchType = scanner.nextInt();
        if(searchType!=1 && searchType!=2)
        {
            System.out.print("您输入的类型有误，请重新输入！");
            throw new RuntimeException();
        }
        return searchType;
    }

    /**
     * Description:使用sql拼接查询学生信息
     * @version:1.0
     * @param searchType
     * @return Student
     */
    private Student serchStudent (int searchType)
    {
        String sql = "select * from examstudent where";
        Scanner scanner = new Scanner(System.in);
        //根据用户的输入类型，提示用户输入信息；若searchType=1,提示输入身份证号，若searchType=2,提示输入准考证号；
        //并根据searchType，拼接sql；
        if(searchType==1)
        {
            System.out.print("请输入身份证号：");
            String idCard = scanner.next();
            sql = sql + " idCard = '" + idCard + "'";
        }else
        {
            System.out.print("请输入准考证号：");
            String examCard = scanner.next();
            sql = sql + " examCard = '" + examCard + "'";
        }
        //执行sql语句
        Student student = getStudent(sql);
        return student;
    }

    /**
     * Description:使用Statement对象根据传入的sql返回student对象
     * @version：1.0
     * @param sql
     * @return
     */
    private Student getStudent (String sql)
    {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Student student = null;

        try
        {
            connection = JDBCTools.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if(resultSet.next())
            {
                student = new Student(resultSet.getInt(1),resultSet.getInt(2),
                        resultSet.getString(3),resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),resultSet.getInt(7));
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }finally
        {
            JDBCTools.releaseSource(connection,statement,resultSet);
        }
        return student;
    }

    /**
     * Description:打印学生信息
     * @param student
     */
    private void printStudent (Student student)
    {
        if(student!=null)
        {
            System.out.println(student);
        }else
        {
            System.out.println("查无此人");
        }
    }

    /**
     * title:③
     * Description:java.sql.PreparedStatement：1）是Statement的子接口，可以传入带sql占位符的sql语句，并且提供了补充占位
     * 符变量的方法；2）使用connection对象的preparedStatement(sql)方法创建该对象；3）可以调用该对象的
     * setXxx(int index,Object value)方法设置占位符的值；4)可以调用executeUpdate()或executeQuery()或execute()方法执行sql
     * 语句，执行时不在需要传入sql语句；
     */
    @Test
    public void PreparedStatementTest()
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try
        {
            connection = JDBCTools.getConnection();
            String sql = "insert into customers (name,email,birth) values (?,?,?)";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,"王恩");
            preparedStatement.setString(2,"18625778058@163.com");
            preparedStatement.setDate(3,new Date(new java.util.Date().getTime()));

            preparedStatement.executeUpdate();
        } catch (Exception e)
        {
            e.printStackTrace();
        }finally
        {
            JDBCTools.releaseSource(connection,preparedStatement,null);
        }
    }

    /**
     * Description：使用preparedStatement对象将student对象插入数据表中
     * @version: 2.0
     * @param student
     */
    public void InsertStudent2(Student student)
    {
        String sql = "insert into examstudent values (?,?,?,?,?,?,?)";
        JDBCTools.update(sql,student.getFlowId(),student.getType(),student.getIdCard(),student.getExamCard(),
                student.getStudentName(),student.getLocation(),student.getGrade());
    }

    /**
     * Description:使用PreparedStatement对象根据传入的sql返回student对象
     * @version：2.0
     * @param sql
     * @param args
     * @return Student
     */
    private Student getStudent (String sql ,Object ...args)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Student student = null;

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
                student = new Student();
                student.setFlowId(resultSet.getInt(1));
                student.setType(resultSet.getInt(2));
                student.setIdCard(resultSet.getString(3));
                student.setExamCard(resultSet.getString(4));
                student.setStudentName(resultSet.getString(5));
                student.setLocation(resultSet.getString(6));
                student.setGrade(resultSet.getInt(7));
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }finally
        {
            JDBCTools.releaseSource(connection,preparedStatement,resultSet);
        }
        return student;
    }


    /**
     * Description:使用PreparedStatement对象根据传入的sql返回customers对象
     * @param sql
     * @param args
     * @return customers
     */
    private Customers getCustomer (String sql ,Object ...args)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Customers customers = null;

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
                customers = new Customers();
                customers.setId(resultSet.getInt(1));
                customers.setName(resultSet.getString(2));
                customers.setEmail(resultSet.getString(3));
                customers.setBirth(resultSet.getDate(4));
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }finally
        {
            JDBCTools.releaseSource(connection,preparedStatement,resultSet);
        }
        return customers;
    }

    /**
     * Description:手写实现通用的查询方法:根据Class对象和sql的不同，返回不同的查询结果
     * @version 1.0
     * @param clazz
     * @param sql
     * @param args
     * @param <T>
     * @return
     */
    public <T> T getObject(Class<T> clazz, String sql, Object... args) {
        T entity = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            //1. 得到 ResultSet 对象
            connection = JDBCTools.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            resultSet = preparedStatement.executeQuery();

            //2. 得到 ResultSetMetaData 对象
            ResultSetMetaData rsmd = resultSet.getMetaData();

            //3. 创建一个 Map<String, Object> 对象, 键: SQL 查询的列的别名,
            //值: 列的值
            Map<String, Object> values = new HashMap<>();

            //4. 处理结果集. 利用 ResultSetMetaData 填充 3 对应的 Map 对象
            if(resultSet.next()){
                for(int i = 0; i < rsmd.getColumnCount(); i++){
                    String columnLabel = rsmd.getColumnLabel(i + 1);
                    Object columnValue = resultSet.getObject(i + 1);

                    values.put(columnLabel, columnValue);
                }
            }

            //5. 若 Map 不为空集, 利用反射创建 clazz 对应的对象
            if(values.size() > 0){
                entity = clazz.newInstance();

                //5. 遍历 Map 对象, 利用反射为 Class 对象的对应的属性赋值.
                for(Map.Entry<String, Object> entry: values.entrySet()){
                    String fieldName = entry.getKey();
                    Object value = entry.getValue();
                    ReflectUtils.setFieldValue(entity, fieldName, value);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCTools.releaseSource(connection, preparedStatement, resultSet);
        }

        return entity;
    }

    @Test
    public void getObjectTest()
    {
        String sql1 = "select id,name,email,birth from customers where id = ?";
        String sql2 = "select flowId,type,idCard,examCard,studentName,location,grade" +
                " from examstudent where flowId = ?";
        Customers customers = getObject(Customers.class,sql1,1);
        System.out.println(customers);

        Student student = getObject(Student.class,sql2,1);
        System.out.println(student);
    }


}
