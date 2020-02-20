#### DAO（Data Access Object）

###### 1.为什么要使用Dao?(Why)

实现功能的模块化，利于代码的维护和扩展；Dao可以被子类继承或直接使用；

###### 2.什么是Dao?(What)

确保以面向对象的方式编写访问数据信息的程序，是一堆访问数据信息的类；包含了对数据的CURD操作，而不包含任何业务相关的信息；

###### 3.如何使用Dao?（How）

- 编写通用的Insert,Delete,Update,Select程序

  ```Java
  //Insert,Delete,Update操作都可以
  void update(String sql,Object...args){}
  ```

  ```Java
  //查询一条记录，返回对应的对象
  <T> T get(Class<T> clazz,String sql,Object...args){}
  ```

  ```Java
  //查询多条记录，返回对应对象的集合
  <T> List<T> getForList(Class<T> clazz,String sql,Object...args){}
  ```

  ```Java
  //返回某条记录的某一个字段的值或一个统计的值
  <E> E getForValue(String sql,Object...args){}
  ```
  
  ```Java
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
  ```
  
  

###### 4.BeanUtils---操作Java属性的工具包

- 面向对象的JDBC编程中，Java类的属性值通过getter,setter来获取或定义；

- 由此引入操作Java类的属性的工具包BeanUtils,通过setProperty()，getProperty()方法来获取或设置Java类的属性值；   

- 使用时导入commons-beanutils-xxxxx.jar和commons-logging-xxxx.jar

  ```Java
  @Test
      public void BeanUtilsTest() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException
      {
          Object object = new Student();
  
          BeanUtils.setProperty(object,"studentName","复兴");
  
          System.out.println(object);
  
          Object studentNameValue = BeanUtils.getProperty(object,"studentName");
  
          System.out.println(studentNameValue);
      }
  ```

###### 5.重构Dao的查询方法抽离出DaoUtils工具类

```Java
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
```

###### 6.JDBC元数据测试

- DatabaseMetaData:描述数据库的元数据对象，用于获取数据库的整体综合信息；
- ResultSetMetaData:描述结果集的元数据对象，用于获取关于 ResultSet 对象中列的类型和属性信息的对象；

```Java
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
```

###### 7.获取数据库自动生成的主键

- 应用场景：A用户买若干件商品，下了一个订单，要想保证交易成功，用户选购商品，下订单须在一个事务中执行；1)商品要想准确的插入订单表，就要准确知道订单的id是多少；因此，这里插入订单记录的同时获取数据库自动生成的订单的主键就很有必要了

- 数据表之间的关系？

  - [x] 用户表与订单表：1：n
  - [ ] 订单表与商品表：1：n
  - [ ] 用户表与商品表：n：n

- 用户浏览商品后，加入购物车，选中购物车中的若干商品，加入订单；商品要知道订单的主键就必须获得订单的主键；

- 具体如何获取数据库自动生成的主键呢？

  1. 通过connection对象的重载的preparedStatement()方法，获取返回自动生成主键的preparedStatement对象
  2. 通过prepatedStatement对象的getGeneratedKeys()方法获取包含了新增主键的ResultSet对象
  3. 从resultSet对象中取出该主键

- 代码示例：

  ```Java
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
  ```

###### 8.Oracle的大数据类型LOB(Large Object)

- LOB是用来存储大量的二进制数据和文本数据的一种数据类型
- 一个LOB类型的字段可存储4GB的数据
- LOB分为两种类型：内部LOB和外部LOB
  1. 内部LOB将数据以字节流的形式存储在数据库的内部；因此内部LOB的许多操作都都可参与事务，也可以像处理普通数据一样对其进行备份和恢复操作；
  2. Oracle支持三种类型的内部LOB:1)BLOB（二进制数据）；2）ClOB（单字节字符数据）;3）NLOB（多字节字符数据）；
  3. CLOB和NLOB类型适用于存储超长的文本数据，BLOB类型适用于存储大量的二进制数据，如图像，视频，音频，文件；
  4. 目前只支持一种外部LOB类型，即BFILE类型，在数据库内，该类型仅存储数据在OS中的位置信息，而数据的实体以外部文件的形式存在于OS的文件系统中，因而，该类型所表示的数据是只读的，不参与事务。该类型可帮助用户管理大量的由外部程序访问的文件；

###### 9.MySql的 BLOB类型

- MySql中，BLOB是一个二进制大型对象类型，是一个可以存储大量数据的容器，可以容纳不同大小的数据；

- MySql的四种BLOB类型（除了在存储的最大信息上不同外，它们是等同的）

  1. TinyBlob类型：最多容纳255字节的数据；
  2. BLob类型：最多容纳65KB字节的数据；
  3. MediumBlob：最多容纳16M字节的数据；
  4. LongBlob：最多容纳4G字节的数据；

- 需要注意的是，如果存储的数据量过大，数据库的性能会下降；

- 插入BLob类型的数据必须使用PrepareStatement对象，因为BLob类型的数据无法使用字符串拼接

- 写入Blob类型的数据时，调用SetBlob(int index,InputStream inputstream)方法

- 从数据库中读取BLob类型的数据时，使用getBlob()方法读取到Blob对象，使用该对象的getBinaryStream()方法得到输入流，在使用IO操作即可；

- 代码示例

  ```Java
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
  ```

  

