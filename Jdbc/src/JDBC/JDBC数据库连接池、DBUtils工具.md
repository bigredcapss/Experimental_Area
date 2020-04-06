#### JDBC数据库连接池、DBUtils工具

###### 1.JDBC数据库连接池的必要性

- 在连接池技术出现前，传统的做法是怎么做的？
- 这种做法存在什么样的问题？
- 为了解决传统做法中存在的问题，连接池是怎么具体解决的？
- 连接池的基本思想是什么？
- 数据库连接池技术的优点？

###### 2.两种古老的开源的数据库连接池

- JDBC的数据库连接池，使用javax.sql.DataSource来表示，DataSource只是一个接口，该接口通常由服务器（WebLogic,WebSphere,Tomcat)提供实现，也有一些开源组织提供实现；
- DBCP数据库连接池与C3P0数据库连接池；
- DataSource通常被称为数据源，它包含连接池和连接池管理两个部分；
  1. DBCP数据源：是Apach组织下的开源连接池实现，该连接池依赖该组织下的另一个开源系统Common-Pool，要使用该连接池实现，要增加commons-dbcp.jar[连接池的实现jar包]和commons-pool.jar[连接池的依赖库]；
  2. Tomcat的连接池正是采用该连接池实现的，该数据库连接池既可以与应用服务器整合使用，也可以由应用程序独立使用；
- 使用连接池的编码思路:
  1. 创建数据源实例；
  2. 为数据源实例指定必须的属性；
  3. 为数据源实例指定相关的属性；
  4. 从数据源中获取数据库连接；

- DBCP数据源代码示例

```Java
/**
     * DBCP数据源实例设定额外的属性测试
     * @throws SQLException
     */
    @Test
    public void dbcpPoolTest_Two() throws SQLException
    {
        //创建数据源实例
        BasicDataSource dataSource = new BasicDataSource();
        //为数据源实例指定必须的属性
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setUrl("jdbc:mysql:///test");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        //为数据源实例指定额外的属性
        dataSource.setInitialSize(10);
        dataSource.setMaxActive(50);
        //从数据源中获取数据库连接
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

    /**
     * DBCP数据源工厂加载properties配置文件测试
     * 注意：1）使用BasicDataSourceFactory加载properties配置文件，配置文件的键需要来自BasicDataSource的属性；
     * 2）通过BasicDataSourceFactory的createDataSource()方法加载配置文件创建数据源实例；
     * @throws Exception
     */
    @Test
    public void dbcpWithDataSourceFactoryTest() throws Exception
    {
        Properties properties = new Properties();
        InputStream inputStream = JDBCTestFive.class.getClassLoader().getResourceAsStream("JDBC/dbcp.properties");
        properties.load(inputStream);

        DataSource dataSource = BasicDataSourceFactory.createDataSource(properties);
        System.out.println(dataSource.getConnection());

        BasicDataSource basicDataSource = (BasicDataSource) dataSource;
        System.out.println(basicDataSource.getMaxActive());
    }
```

- C3P0数据源代码示例

  ```Java
      /**
       * c3p0数据源测试
       * @throws Exception
       */
      @Test
      public void c3p0PoolTest() throws Exception
      {
          //创建数据源实例
          ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
  
          //为数据源实例指定必须的属性
          comboPooledDataSource.setDriverClass("com.mysql.jdbc.Driver");
          comboPooledDataSource.setJdbcUrl("jdbc:mysql:///test");
          comboPooledDataSource.setUser("root");
          comboPooledDataSource.setPassword("root");
  
          //从c3p0数据源中获取数据库连接
          Connection connection = comboPooledDataSource.getConnection();
          System.out.println(connection);
      }
  
  
      /**
       * 读取c3p0-config.xml配置文件，获取连接池属性
       * @throws SQLException
       */
      @Test
      public void c3p0PoolWithConfigTest() throws SQLException
      {
          //创建数据源实例
          //注意:该配置文件只能被放在src目录下
          DataSource dataSource = new ComboPooledDataSource("c3p0");
          System.out.println(dataSource.getConnection());
  
          ComboPooledDataSource comboPooledDataSource = (ComboPooledDataSource) dataSource;
          System.out.println(comboPooledDataSource.getMaxStatements());
          System.out.println(comboPooledDataSource.getAcquireIncrement());
      }
  ```

###### 3.Apach----DBUtils工具

- commons-dbutils是Apach组织提供的一个开源JDBC工具类库，它是对JDBC的简单封装，使用DBUtils能极大的简化JDBC的编码，同时也不会影响JDBC的性能；
- API学习
  1. org.apache.commons.dbutils.QueryRunner;
  2. org.apache.commons.dbutils.ResultSetHandler;
  3. 工具类：org.apache.commons.dbutils.Dbutils;
- 应用范围：在一些对性能要求比较高的项目中，使用原生Jdbc访问数据库，这是可以选择DBUtils,当然我们也可以封装我们自己的Jdbc库；
- 编码过程：IDBUtilsDAO-->DBUtilsDAO-->CustomersDao-->CustomersDaoTest