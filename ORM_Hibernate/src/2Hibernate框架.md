#### 2Hibernate框架

###### 1.主键生成策略

| identity    | 采用数据库生成的主键，用于为long、short、int类型生成唯一标识,  Oracle 不支持自增字段。 | <id name="id" column="id" type="long">    <generator class="identity"  />  </id> |
| ----------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| sequence    | DB2、Oracle均支持的序列，用于为long、short或int生成唯一标识。  需要oracle创建sequence。 | <id  name="id" column="id" type="long">    <generator  class="sequence">      <param  name="sequence">seq_name</param>    </generator>  </id> |
| native      | **根据底层数据库的能力，从identity、sequence、hilo中选择一个，灵活性更强。** | <id  name="id" column="id">    <generator class="native"  />  </id> |
| increment   | 个是由Hibernate在内存中生成主键，每次增量为1，不依赖于底层的数据库，因此所有的数据库都可以使用 | <id  name="id" column="id">    <generator class="increment"  />  </id> |
| uuid.hex    | 使用一个128-bit的UUID算法生成字符串类型的标识符              | <id  name="id" column="id">    <generator class="uuid.hex"  />  </id> |
| uuid.string | hibernate会算出一个16位的值插入                              |                                                              |

###### 2.Hibernate与Java的数据类型对应

如图所示：
![hibernate-java-datatype](https://github.com/BigRedCaps-Pro/Experimental_Area/tree/master/ORM_Hibernate/image/hibernate-java-datatype.png)

![hibernate-java-datatype2](https://github.com/BigRedCaps-Pro/Experimental_Area/tree/master/ORM_Hibernate/image/hibernate-java-datatype2.png)

- 流类型

  ```Java
  FileInputStream in = new FileInputStream(new File("测试文档.txt"));
    int length = in.available();
    byte[] b = new byte[length];
    in.read(b);
  ```

- 时间戳类型

  ```Java
  new Timestamp(System.currentTimeMillis())
  ```

- 查询的排序

  ```Java
  session.createQuery("from DataType as d order by d.id asc");
  ```

- 分页

  ```java
  query.setFirstResult(2);   query.setMaxResults(2);
  ```

- 方法比较

     list()/iterator()的区别？

     ```java
     编写代码的方式不同 list()和iterator()
     底层发送的SQL语句不同：
     list()直接一次性获取到所有持久化类的对象；
     iterator()先获取的是所有的数据的id值。当真正的遍历使用数据的时候再发送select语句。因此该方法一定要处于session会话中； 
     list发送的查询语句只有1条；
     Iterator发送多条查询语句，因此iterator的效率低下。懒汉式(iterator)  饿汉式(list)；
         
     list()在表中有少量条数据时使用；
     iterator()如果表中有N条数据使用；
     ```

     get()/load()的区别？

     ```java
     get()如果没有找到持久化类返回null，有可能导致空指针异常；
     
     load()如果没有找到持久化类直接抛出异常；
     
     get()是直接加载数据，load()是延迟加载，当使用被加载数据的时候才发送SQL；
     
     简而言之：Hibernate对于load()认为数据库一定存在，因此可以放心的使用代理进行延迟加载，如果使用中发现了问题，那么只能抛出异常。而对于get方法一定要获取真实的数据，否则返回null。
     
     e.g:    
     DataType dataType1 = (DataType) session.load(DataType.class, new Long(1));
     DataType dataType2 = (DataType) session.load(DataType.class, new Long(1));
     System.out.println(dataType1);   // 延迟加载，需要使用才发送SQL语句
     System.out.println(dataType2);   // 从一级缓存中获取持久化对象
     System.out.println(dataType1 == dataType2);
     session.getTransaction().commit();
     
     ```

-  在实际开发中需要在hbm文件中使用的type属性值是指定的类型。那

  么指定的类型一般的是基于hibernate的类型。

-  当然在实际过程中也可以在hbm文件中指定java类型。

- Demo

  ```java
  public class DataType 
  {
      private long id;        
      private boolean tag;              
      private Date createDate;
      private char vip;      
      private Timestamp logTime;   
      private byte[] description;
      ...
  ｝
  
  ```

  ```xml
  <hibernate-mapping>
      <class name="bean.DataType" table="datatype">
       <id name="id" column="id" type="long">
         <generator class="increment"></generator>
       </id>
       <property name="tag" column="tag" type="boolean"></property>
       <property name="createDate" column="createDate" type="date"></property>
       <property name="vip" column="vip" type="character"></property>
       <property name="logTime" column="logTime" type="timestamp"></property>
       <property name="description" column="description" type="binary"></property>
    </class>
  </hibernate-mapping>
  
  ```

###### 3.Hibernate的执行流程

1. 应用程序先调用Configuration类,该类读取Hibernate配置文件及映射文件中的信息；

2. 并用这些信息生成一个SessionFactory对象；

3. 然后从SessionFactory对象生成一个Session对象；

4. 并用Session对象生成Transaction对象；

   1. 可通过Session对象的get(),load(),save(),update(),delete()和saveOrUpdate()等方法对PO进行加载、保存、更新、删除、 等操作；
   2. 在查询的情况下，可通过Session对象生成一个Query对象，然后利用Query对象执行查询操作；如果没有异常，Transaction对象将提交这些操作到数据库中；

5. 如图所示：

   ![hibernate执行流程](https://github.com/BigRedCaps-Pro/Experimental_Area/tree/master/ORM_Hibernate/image/hibernate执行流程.png)

###### 4.集合映射

- 配置文件

```xml
<class name="Worker" table="t_worker">
        <id name="workerId" column="id">
            <generator class="native"></generator>
        </id>
        <property name="workerName"></property>

        <!--
            set集合属性的映射
                name: 指定要映射的set集合的属性
                table: 集合属性要映射到的表
                key:指定集合表(address)的外键字段
                element:指定集合表的其他字段
                    type:元素类型，一定要指定
         -->
        <set name="addressSet" table="t_address">
            <key column="wid"></key>
            <element column="address" type="string"></element>
        </set>

        <!--
            list集合映射
                list-index:指定的是排序列的名称 (因为要保证list集合的有序)
         -->
        <list name="addressList" table="t_addressList">
            <key column="wid"></key>
            <list-index column="id_index"></list-index>
            <element column="address" type="string"></element>
        </list>

        <!--
            map集合的映射
                key:指定外键字段
                map-key:指定map的key
                element:指定map的value
         -->
        <map name="addressMap" table="t_addressMap">
            <key column="wid"></key>
            <map-key column="addressCode" type="string" ></map-key>
            <element column="address" type="string" ></element>
        </map>
    </class>
```

- 工人实体类文件

  ```Java
  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public class Worker
  {
      private int workerId;
      private String workerName;
  
      /*
       *一个工人，对应的多个地址
       */
      private Set<String> addressSet;//set集合映射
  
      private List<String> addressList = new ArrayList<String>();//List集合映射
  
      private String[] addressArray; // 数组映射：映射方式和list相同<array name=""></array>
  
      private Map<String,String> addressMap = new HashMap<String, String>();//Map集合映射
  }
  ```

- 注意

  集合映射会生成对应的集合表，但不会新建集合的实体；

###### 5.一对多与多对一映射

- 部门实体与员工实体

  ```Java
  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public class Dept
  {
      private int deptId;
  
      private String deptName;
  
      // 【一对多】 一个部门对应多个员工
      private Set<Employee2> empSet = new HashSet<>();
  }
  
  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public class Employee2
  {
      private int empId;
  
      private String empName;
  
      private double salary;
  
      // 【多对一】多个员工与一个部门
      private Dept dept;
      
  }
  ```

- 部门映射文件（Dept.hbm.xml）与员工映射文件（Employee2.hbm.xml）

  ```xml
  <!--       部门：     -->
  <class name="Dept" table="t_dept">
          <id name="deptId">
              <generator class="native"></generator>
          </id>
          <property name="deptName" length="20"></property>
          <!--
              一对多关联映射配置 （通过部门管理到员工）
              Dept 映射关键点：
              1.  指定映射的集合属性： "emps"
              2.  集合属性对应的集合表： "t_employee"
              3.  集合表的外键字段   "t_employee. dept_id"
              4.  集合元素的类型
              注意：这里的table="t_employee"也可以省略
  
              inverse,cascade属性：
              inverse=false  set集合映射的默认值； 表示有控制权
              cascade="save-update,delete"
           -->
          <set name="empSet">
              <key column="dept_id"></key>
              <one-to-many class="Employee2"/>
          </set>
      </class>
  ```

  ```xml
  <!--     员工     -->
  <class name="Employee2" table="t_employee">
          <id name="empId">
              <generator class="native"></generator>
          </id>
          <property name="empName" length="20"></property>
          <property name="salary" type="double"></property>
  
          <!--
              多对一映射配置
              Employee 映射关键点：
              1.  映射的部门属性：dept
              2.  映射的部门属性，对应的外键字段: dept_id
              3.  部门的类型
           -->
          <many-to-one name="dept" column="dept_id" class="Dept"></many-to-one>
  
      </class>
  ```

- 在一对多与多对一的关联关系中，保存数据最好的**通过多的一方来维护关系**，这样可以减少update语句的生成，从而提高hibernate的执行效率！

  配置一对多与多对一，这种叫“双向关联”

  只配置一对多，      叫“单项一对多”

  只配置多对一，      叫“单项多对一”

- 注意：

  ​     **配置了哪一方，哪一方才有维护关联关系的权限！**

###### 6.inverse属性

- Inverse属性：

  在维护关联关系的时候起作用；表示控制权是否转移；(在一的一方起作用)；**【只能设置在一的一方】**

- Inverse , 控制反转：

  Inverse = false 不反转，当前方有控制权；

  Inverse =true 控制反转，当前方没有控制权；

- 维护关联关系中，是否设置inverse属性：

  1. 保存数据 

     **有影响；**如果设置控制反转,即inverse=true, 然后通过部门方维护关联关系。在保存部门的时候，同时保存员工， 数据会保存，但**关联关系不会维护。即外键字段为NULL**

  2. 获取数据
  
     **无影响；**inverse属性无论为true或false对获取数据没有影响；
  
     但这里遇到**org.hibernate.LazyInitializationException: illegal access to loading collection【即懒加载异常】**
  
  3. 解除关联关系
  
     inverse=false，当前方（部门）有控制权，可以解除关联，结果是将员工表的外键字段设置为NULL；
  
     inverse=true，当前方(部门)没有控制权，不能解除关联关系(不会生成update语句,也不会报错)；
  
  4. 删除数据
  
     inverse=false, 有控制权，可以删除；先清空外键引用，再删除数据；
  
     inverse=true,  没有控制权， 如果删除的记录有被外键引用，会报错，违反主外键引用约束；如果删除的记录没有被引用，可以直接删除;

###### 7.cascade级联操作

- cascade 表示级联操作 **【可以设置到一的一方或多的一方】**
  - none:  不级联操作， 默认值；
  -  save-update :  级联保存或更新；
  - delete :    级联删除；
  - save-update,delete ： 级联保存、更新、删除；
  - all : 同上，级联保存、更新、删除；
- cascade级联设置对多对一、一对多，多对多等关系来说是一样的；

###### 8.多对多映射配置

- 实体类

  ```Java
  //项目实体
  /**
   * @Description:项目实体
   * @Author:BigRedCaps
   */
  public class Project
  {
      private int prj_id;
      private String prj_name;
      // 项目下的多个员工
      private Set<Developer> developers = new HashSet<Developer>();
  ...
  }
  
  //开发者实体
  /**
   * @Description:开发者实体
   * @Author:BigRedCaps
   */
  public class Developer
  {
      private int d_id;
      private String d_name;
      // 开发人员，参与的多个项目
      private Set<Project> projects = new HashSet<Project>();
  ...
  }
  ```

- hibernate配置文件

  ```xml
  <!--Project.hbm.xml文件-->
  <hibernate-mapping package="bean">
      <class name="Project" table="t_project">
          <id name="prj_id">
              <generator class="native"></generator>
          </id>
          <property name="prj_name" length="20"></property>
          <!--
              多对多映射:
              1.映射的集合属性： “developers”
              2.集合属性，对应的中间表： “t_relation”
              3.外键字段:  prjId
              4.外键字段，对应的中间表字段:  did
              5.集合属性元素的类型
           -->
          <set name="developers" table="t_relation" cascade="save-update" inverse="false">
              <key column="prjId"></key>
              <many-to-many column="did" class="Developer"></many-to-many>
          </set>
  
      </class>
  </hibernate-mapping>
  
  
  <!-- Developer.hbm.xml文件 -->
  <hibernate-mapping package="bean">
  
      <class name="Developer" table="t_developer">
          <id name="d_id">
              <generator class="native"></generator>
          </id>
          <property name="d_name" length="20"></property>
  
          <!--
              多对多映射配置： 员工方
                  name：指定映射的集合属性
                  table：集合属性对应的中间表
                  key：指定中间表的外键字段(引用当前表t_developer主键的外键字段)
                  many-to-many：
                      column： 指定外键字段对应的项目字段
                      class：集合元素的类型
           -->
          <set name="projects" table="t_relation" cascade="save-update" inverse="false">
              <key column="did"></key>
              <many-to-many column="prjId" class="Project"></many-to-many>
          </set>
      </class>
  
  </hibernate-mapping>
  ```

###### 9.多对多关联关系维护

```xml
<set name="projects" table="t_relation" cascade="save-update" inverse="false">
```

