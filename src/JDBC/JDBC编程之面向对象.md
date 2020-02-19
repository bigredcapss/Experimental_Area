### 以面向对象的方式编写JDBC程序

###### 1.面向对象之插入学生记录

1. 建立数据库表对应的实体类Student

   ```Java
   Class Student
   {
   	private int flowId;
   	private int type;
   	private String idCard;
   	...
   }
   ```

2. 从控制台获取学生信息，并封装成Student对象

   ```Java
   public Student getStudentFromConsole()
       {
           Scanner scanner = new Scanner(System.in);
           Student student = new Student();
   
           System.out.print("Please Input flowId:");
           student.setFlowId(scanner.nextInt());
   
           System.out.print("Please Input type:");
           student.setType(scanner.nextInt());
           ...
        }
   ```

3. 编写方法向数据表中插入一条学生记录

   ```Java
   public void InsertStudent(Student student)
       {
           //拼接sql语句
           String sql = "insert into examStudent values("+student.getFlowId()+","+student.getType()+"," +
                   "'"+student.getIdCard()+"','"+student.getExamCard()+"','"+student.getStudentName()+"'," +
                   "'"+student.getLocation()+"',"+student.getGrade()+")";
           //调用JDBCTools类的update方法
           JDBCTools.update(sql);
       }
   ```

###### 2.面向对象之查询学生记录

1. 获取查询类型

   ```Java
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
   ```

2. 具体查询学生信息

   ```Java
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
               System.out.println(sql);
           }else
           {
               System.out.print("请输入准考证号：");
               String examCard = scanner.next();
               sql = sql + " examCard = '" + examCard + "'";
               System.out.println(sql);
           }
           //执行sql语句
           Student student = getStudent(sql);
           return student;
       }
   ```

   ```Java
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
   ```

3. 打印学生信息

```Java
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
```

###### 3.使用Statement对象执行sql语句存在的问题

- [x] 使用PreparedStatement解决statement对象执行sql语句时需要拼接sql语句的问题

- [x] 使用PreparedStatement解决statement对象执行sql语句时sql注入的问题

- [x] 使用PreparedStatement解决statement对象批量执行sql语句时的效率低下问题

  - 对PreparedStatement对象的使用

  1. 调用connection对象的prepatedStatement()方法创建PreparedStatement对象

  2. 调用该对象的setXxx()方法向占位符设置值

  3. 调用该对象的execute()或executeUdate()或executeQuery()执行sql语句
  
  4. 具体代码①
  
     ```Java
     /**
          * 更新方法
          * @version: 2.0
          * @param sql
          * @param args sql占位符的可变参数
          */
         public static void update(String sql ,Object...args)
         {
             Connection connection = null;
             PreparedStatement preparedStatement = null;
     
             try
             {
                 connection = JDBCTools.getConnection();
                 preparedStatement = connection.prepareStatement(sql);
                 //向占位符填值
                 for (int i = 0; i < args.length; i++)
                 {
                     preparedStatement.setObject(i+1,args[i]);
                 }
                 preparedStatement.executeUpdate();
             } catch (Exception e)
             {
                 e.printStackTrace();
             }finally
             {
               JDBCTools.releaseSource(connection,preparedStatement,null);
             }
         }
     ```
  
  5. 具体代码②
  
     ```Java
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
     ```
  
  6. 具体代码③
  
     ```Java
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
     ```

###### 4.使用JDBC元数据和反射编写通用的查询方法解决查询某一对象的记录时，代码重复冗余度高的问题

- 思路：

1. 使用sql查询，获取结果集ResultSet对象

2. 利用反射创建实体类的对象

3. 利用ResultSetMetaData获取结果集列的别名（与实体类的属性名对应）

   - 通过列的别名和实体类的属性名一致来建立一种联系

4. 获取结果集的每一列的值，与列的别名组合成一个Map<Key,Value>

5. 再利用反射遍历Map<Key,Value>为实体类对象的对应属性赋值

   ```Java
   public <T> T getObject(Class<T> clazz, String sql ,Object...args)
       {
           Connection connection = null;
           PreparedStatement preparedStatement = null;
           ResultSet resultSet = null;
           T entity = null;
   
           try
           {
               connection = JDBCTools.getConnection();
               preparedStatement = connection.prepareStatement(sql);
               //向占位符填值
               for (int i = 0; i < args.length; i++)
               {
                   preparedStatement.setObject(i+1,args[i]);
               }
               resultSet = preparedStatement.executeQuery();
               if(resultSet.next())
               {
                   //利用反射创建对象
                   entity = clazz.newInstance();
                   //通过解析sql语句来判断选择哪些列，以及需要为entity对象的哪些属性赋值
                   Map<String,Object> values = new HashMap<>();
                   ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                   //使用resultMetaData和ResultSet给Map添加key-value对；
                   while(resultSet.next())
                   {
                       for (int i = 0; i < resultSetMetaData.getColumnCount(); i++)
                       {
                           String columnLabel =resultSetMetaData.getColumnName(i+1);
                           Object columnValue = resultSet.getObject(columnLabel);
                           values.put(columnLabel,columnValue);
                       }
                   }
                   System.out.println(values);
                   //遍历Map集合并通过反射工具类为对应的实体属性赋值
                   if(values.size()>0)
                   {
                       for (Map.Entry<String,Object> entry : values.entrySet())
                       {
                           String fieldName = entry.getKey();
                           Object fieldValue = entry.getValue();
                           ReflectUtils.setFieldValue(entity,fieldName,fieldValue);
                       }
                   }
               }
           } catch (Exception e)
           {
               e.printStackTrace();
           }
           finally
           {
               JDBCTools.releaseSource(connection,preparedStatement,resultSet);
           }
           System.out.println(entity);
           return entity;
   
       }
   ```
   
   