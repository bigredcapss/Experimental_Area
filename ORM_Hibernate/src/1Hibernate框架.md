一、Hibernate框架

###### 1.ORM概念

ORM即对象关系映射，主要解决了以面向对象的方式对数据库进行读写操作（存储：把对象的信息直接存储到数据库中； 获取： 直接从数据库拿到一个对象；）；而Hibernate是ORM的典型实现；

映射：①对象与表的映射；②属性与字段的映射；③数据库字段类型与对象属性类型的映射；

###### 2.回想一下JDBC是如何以面向对象的方式编程的

一直说这些ORM框架都是对JDBC的封装，但我们思考一下它是怎么封装的呢？

在以面向对象的方式编写JDBC程序时：是这样的一个思路：

以编写通用的查询方法为例：

①利用SQL进行查询，得到结果集，进而得到结果集的元数据对象ResultSetMetaData；

②利用反射创建实体类的对象；

③获取结果集的列的别名（对应实体类的属性）；

④获取结果集每一列的值，结合③得到一个Map对象（键：列的别名，值：列的值）；

⑤再利用反射为实体类的对象的对应属性赋值（属性即为Map的键，值即为Map的值）

###### 3.Hibernate如何用？

①下载源码，引入jar包；

②写对象以及对象的映射文件;e.gEmployee.java、Employee.hbm.xml

③写Hibernate的主配置文件hibernate.cfg.xml；

- 数据库连接配置
- 加载所用的对象映射文件

④写Junit测试程序；

###### 4.Hibernate 初步了解

1. Configuration:加载配置文件的管理类对象；

   Configuration类负责管理Hibernate的配置信息。包括如下内容：

   - Hibernate运行的底层信息：数据库的URL、用户名、密码、JDBC驱动类，数据库Dialect,数据库连接池等（对应 **hibernate.cfg.xml** 文件）；
   - 持久化实体类与数据表的映射关系（*.**hbm.xml** 文件）

   ```Java
   Configuration config = new Configuration();
   config.configure();// 默认加载src/hibenrate.cfg.xml文件
   //config.configure("src/conf/hibernate.cfg.xml")
   SessionFactory sf = config.buildSessionFactory();
   ```

   - configure():加载主配置文件的方法；
   - buildSessionFactory():创建session的工厂对象；

2. SessionFactory:创建session的工厂，或者说代表了这个hibernate.cfg.xml配置文件

   - Configuration对象根据当前的配置信息生成 SessionFactory 对象。SessionFactory 对象一旦构造完毕，即被赋予特定的配置信息(SessionFactory 对象中保存了当前的数据库配置信息和所有映射关系以及预定义的SQL语句。同时，**SessionFactory还负责维护Hibernate的二级缓存**)；

   - 是线程安全的；

   - SessionFactory是生成Session的工厂：

   - 构造SessionFactory很消耗资源，一般情况下一个应用中只初始化一个 SessionFactory对象。

   ```Java
   SessionFactory sf = config.buildSessionFactory();
   // 创建session (代表一个会话，与数据库连接的会话)
   Session session = sf.openSession();
   // 开启事务
   Transaction tx = session.beginTransaction();
   //保存-数据库
   session.save(emp);
   ```

   - openSession():创建session对象的方法；
   - getCurrentSession():创建session或取出session对象;(后面介绍两者区别)

3. Session:session对象维护了一个数据库连接(Connection), 代表了与数据库连接的会话。Hibernate最重要的对象： 只要使用hibernate与数据库操作，都会用到这个对象

   - Session是应用程序与数据库之间交互操作的一个单线程对象，是 Hibernate 运作的中心，所有持久化对象必须在 session 的管理下才可以进行持久化操作。此对象的生命周期很短。Session 对象有一个一级缓存，显式执行 flush 之前，所有的持久层操作的数据都缓存在 session 对象处。相当于 JDBC 中的 Connection。
- 持久化类与 Session 关联起来后就具有了持久化的能力。
   - 是线程不安全的
- Session 类的方法：
     1. 取得持久化对象的方法： get() load()；
  2. 持久化对象都得保存，更新和删除：save(),update(),saveOrUpdate(),delete()；
     3. 开启事务: beginTransaction()；
     4. 管理 Session 的方法：isOpen(),flush(), clear(), evict(), close()等
   - beginTransaction():开启一个事务；hibernate要求所有的与数据库有关的操作必须有事务的环境，否则报错；
   - save():插入保存一条记录/一个对象；
   - update():更新记录/对象；
- saveOrUpdate():保存或更新操作；若没有设置主键，执行保存操作；若设置了主键，执行更新操作；
   - get(Employee.class,1):执行主键查询；
   - load(Employee.class,1):执行主键查询，支持懒加载；
   
4. Transaction接口

   - 代表一次原子操作，它具有数据库事务的概念。所有持久层都应该在事务管理下进行，即使是只读操作。

       Transaction tx **=** session.beginTransaction();

   - 常用方法:

     1. commit():提交相关联的session实例；

     1. rollback():撤销事务操作；

     1. wasCommitted():检查事务是否提交；

5. Hibernate提供的各种查询方式

   - HQL查询

     ```Java
     //HQL查询
     Query q = session.createQuery("from Employee where empId=1 or empId=2");
     List<Employee> list = q.list();
     ```

   - SQL查询

     ```Java
     //把每一行记录封装为指定的对象类型
     SQLQuery sqlQuery = session.createSQLQuery("select * from employee").addEntity(Employee.class);
     List list = sqlQuery.list();
     ```

   - Criteria查询

     ```Java
      // QBC查询
     Criteria criteria = session.createCriteria(Employee.class);
     // 条件
     criteria.add(Restrictions.eq("empId", 1));
     List<Employee> list = criteria.list();
     ```

6. Hibernate的CRUD操作

   Hibernate的CRUD操作使用hibernate封装的save,delete,update,saveOrUpdate，createQuery,createSqlQuery,createCriteriaQuery等方法进行对应的CRUD操作.

   - 保存操作（session.save(obj)）

     ```Java
      public void save (Employee emp)
      {
          Session session = null;
          Transaction tx = null;
          try
          {
              session = HibernateUtil.getSession();
              tx = session.beginTransaction();
              //保存操作
              session.save(emp);
              tx.commit();
          } catch (HibernateException e)
          {
              e.printStackTrace();
          } finally
          {
              session.close();
          }
      }
     ```

   - 删除操作（session.delete(obj))

     ```Java
     public void delete (Serializable id)
     {
         Session session = null;
         Transaction tx = null;
         try
         {
             session = HibernateUtil.getSession();
             tx = session.beginTransaction();
             // 先根据id查询对象，再判断删除
             Object obj = session.get(Employee.class, id);
             if (obj != null) {
                 session.delete(obj);
             }
         } catch (Exception e)
         {
             throw new RuntimeException(e);
         } finally
         {
             tx.commit();
             session.close();
         }
     }
     ```

   - 修改操作（session.update(obj))

     ```Java
     public void update (Employee emp)
     {
         Session session = null;
         Transaction tx = null;
         try
         {
             session = HibernateUtil.getSession();
             tx = session.beginTransaction();
             //更新操作
             session.update(emp);
             tx.commit();
         } catch (HibernateException e)
         {
             e.printStackTrace();
         } finally
         {
             session.close();
         }
     }
     ```

   - 查询操作（session.createQuery(sql))

     ```Java
     public List<Employee> getAll()
     {
         Session session = null;
         Transaction tx = null;
         List<Employee> employees = null;
         try
         {
             session = HibernateUtil.getSession();
             tx = session.beginTransaction();
             // HQL查询
             employees = session.createQuery("from Employee").list();
         } catch (Exception e)
         {
             e.printStackTrace();
         } finally
         {
             tx.commit();
             session.close();
         }
         return employees;
     }
     ```

7. 易出现的共性问题易出现的共性问题

   - ClassNotFoundException
   - 如果程序执行程序，hibernate也有生成sql语句，但数据没有结果影响，一般是事务忘记提交

8. Hibernate的主配置文件hibernate.cfg.xml

   <u>Hibernate.cfg.xml</u>

   ​     主配置文件中主要配置：**数据库连接配置信息**、**其他相关配置信息**、**实体类与数据表的映射信息**！

   <u>常用配置查看源码：</u>

   ​     hibernate-distribution-3.6.0.Final\project\etc**\hibernate.properties**

   <u>自动建表：</u>

   ​	#hibernate.hbm2ddl.auto ：**create-drop** 每次在创建sessionFactory时候执行创建表； 当调用sesisonFactory的close方法的时候，删除表！

   ​	#hibernate.hbm2ddl.auto ：**create**  每次都重新建表； 如果表已经存在就先删除再创建

   ​	#hibernate.hbm2ddl.auto： **update** 如果表不存在就创建； 表存在就不创建；

   ​	#hibernate.hbm2ddl.auto ：**validate** (生成环境时候) 执行验证： 当映射文件的内容与数据库表结构不一样的时候就报错！

   ```xml
   <hibernate-configuration>
   	<!-- 通常，一个session-factory节点代表一个数据库 -->
   	<session-factory>
   		<!-- 1. 数据库连接配置 -->
   		<property name="hibernate.connection.driver_class">com.mysql.jdbc.Driver</property>
   		<property name="hibernate.connection.url">jdbc:mysql:///test</property>
   		<property name="hibernate.connection.username">root</property>
   		<property name="hibernate.connection.password">root</property>
   		<!-- 
   			数据库方言配置， hibernate在运行的时候，会根据不同的方言生成符合当前数据库语法的sql
   		 -->
   		<property name="hibernate.dialect">org.hibernate.dialect.MySQL5Dialect</property>
   		
   		<!-- 2. 其他相关配置 -->
   		<!-- 2.1 显示hibernate在运行时候执行的sql语句 -->
   		<property name="hibernate.show_sql">true</property>
   		<!-- 2.2 格式化sql -->
   		<property name="hibernate.format_sql">true</property>
   		<!-- 2.3 自动建表
   		<property name="hibernate.hbm2ddl.auto">update</property>
   		-->
   		
   		<!-- 3. 加载所有映射 -->
   		<mapping resource="bean/Employee.hbm.xml"/>
   	</session-factory>
   </hibernate-configuration>
   ```

9. Hibernate的映射配置

   ```xml
   <?xml version="1.0"?>
   <!DOCTYPE hibernate-mapping PUBLIC 
   	"-//Hibernate/Hibernate Mapping DTD 3.0//EN"
   	"http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd">
   <!-- 映射文件: 映射一个实体类对象；描述一个对象，最终实现可以直接保存对象数据到数据库中。  -->
   <!-- package: 要映射的对象所在的包(可选,如果不指定,此文件所有的类都要指定全路径);
   	auto-import 默认为true， 在写hql的时候自动导入包名;如果指定为false, 再写hql的时候必须要写上类的全名；如：session.createQuery("from bean.Employee").list();
    -->
   <hibernate-mapping package="bean" auto-import="true">
   	
   	<!-- 
   		class 映射某一个对象的(一般情况，一个对象写一个映射文件，即一个class节点);
   		name: 指定要映射的对象的类型
   		table: 指定对象对应的表；
   		如果没有指定表名，默认与对象名称一样 
   	 -->
   	<class name="Employee" table="employee">
   		
   		<!-- 主键 ，映射-->
   		<id name="empId" column="id">
   			<!-- 
   				主键的生成策略
   					identity  自增长(mysql,db2)；
   					sequence  自增长(序列)， oracle中自增长是以序列方法实现；
   					native  自增长【会根据底层数据库自增长的方式选择identity或sequence】如果是mysql数据库, 采用的自增长方式是identity；如果是oracle数据库， 使用sequence序列的方式实现自增长；increment  自增长(会有并发访问的问题，一般在服务器集群环境使用会存在问题。);
   					assigned:  指定主键生成策略为手动指定主键的值;
   					uuid:指定uuid随机生成的唯一的值
   					foreign:(外键的方式， one-to-one讲)
   			 -->
   			<generator class="uuid"/>
   		</id>
   		
   		<!-- 
   			普通字段映射
   			property
   				name:  指定对象的属性名称;
   				column: 指定对象属性对应的表的字段名称，如果不写默认与对象属性一致;
   				length: 指定字符的长度, 默认为255;
   				type: 指定映射表的字段的类型，如果不指定会匹配属性的类型
   					java类型：必须写全名
   					hibernate类型：直接写类型，都是小写
   		-->
   		<property name="empName" column="empName" type="java.lang.String" length="20"></property>
   		<property name="workDate" type="java.util.Date"></property>
   		<!-- 如果列名称为数据库关键字，需要用反引号(``)或改列名。 -->
   		<property name="desc" column="`desc`" type="java.lang.String"></property>
   	</class>
   </hibernate-mapping>
   ```

10. 复合主键映射

   - 实体类与对应的复合主键类；

     ```Java
     /**
      * @Description:复合主键类;该类必须实现序列化接口；
      * @Author:BigRedCaps
      */
     @Data
     @AllArgsConstructor
     @NoArgsConstructor
     public class CompositeKeysBean implements Serializable
     {
         //用户名和地址唯一确定一条记录
         private String userName;
         private String address;
     }
     ```

     ```Java
     /**
      * @Description:用户实体类
      * @Author:BigRedCaps
      */
     @Data
     @NoArgsConstructor
     @AllArgsConstructor
     public class User
     {
         private CompositeKeysBean keys;
         private int age;
     }
     ```

   - 实体类映射文件

     ```Xml
     <hibernate-mapping package="bean" auto-import="true">
         <class name="User">
             <!-- 复合主键映射 -->
             <composite-id name="keys">
                 <key-property name="userName" type="string"></key-property>
                 <key-property name="address" type="string"></key-property>
             </composite-id>
     
             <property name="age" type="int"></property>
         </class>
     </hibernate-mapping>
     ```

   - 测试类

     注意一下代码块是冲突的

     ```Java
     ①
     static
         {
         //加载实体的映射文件(User.hbm.xml)
             sf = new aConfiguration().configure().addClass(User.class).buildSessionFactory();
         }
     ②hibernate.cfg.xml
         <mapping resource="bean/User.hbm.xml"/>
     ```

     ```Java
     //完整测试类
     public class UserCRUDTest
     {
         private static SessionFactory sf;
         static
         {
             sf = new Configuration().configure().addClass(User.class).buildSessionFactory();
         }
     
         /**
          * 保存对象
          * @throws Exception
          */
         @Test
         public void testSave() throws Exception
         {
             Session session = sf.openSession();
             Transaction tx = session.beginTransaction();
     
             // 复合主键对象
             CompositeKeysBean keys = new CompositeKeysBean();
             keys.setAddress("广州棠东");
             keys.setUserName("Jack");
             User user = new User();
             user.setAge(20);
             user.setKeys(keys);
     
             // 保存
             session.save(user);
     
             tx.commit();
             session.close();
         }
     
         @Test
         public void testGet() throws Exception
         {
             Session session = sf.openSession();
             Transaction tx = session.beginTransaction();
     
             //构建主键再查询
             CompositeKeysBean keys = new CompositeKeysBean();
             keys.setAddress("广州棠东");
             keys.setUserName("Jack");
     
             // 主键查询
             User user = (User) session.get(User.class, keys);
             // 测试输出
             if (user != null){
                 System.out.println(user.getKeys().getUserName());
                 System.out.println(user.getKeys().getAddress());
                 System.out.println(user.getAge());
             }
     
             tx.commit();
             session.close();
         }
     }
     
     ```

     