#### Hibernate的缓存，Hibernate中对象的状态，Hibernate的映射

###### 1.Hibernate中对象的状态

- 临时状态：

  ```Java
  使用new操作符初始化的对象不是立刻就持久化的，他们的状态是瞬时的。
  (1) 不处于Session的缓存中，也可以说，不被任何一个Session实例关联。
  (2) 在数据库中没有对应的记录。
  ```

- 持久化状态

  ```Java
  持久实例是任何具有数据库标识的实例。它有持久化管理器Session统一管理，持久实例是在事务中进行操作的———他们的状态在事务结束时同数据库进行同步。当调session/save/saveOrUpdate/get/load/list
  等方法的时候，对象就是持久化状态。处于持久化状态的对象，当对对象属性进行更改的时候，会反映到数据库中!
  (1) 位于一个Session实例的缓存中，也可以说，持久化对象总是被一个Session实例关联。
  (2) 持久化对象和数据库中的相关记录对应。
  (3) Session在清理缓存时，会根据持久化对象的属性变化，来同步更新数据库。
  ```

- 离线对象

  ```Java
  Session关闭之后，持久化对象就变为离线对象。离线表示这个对象不能再与数据库保持同步，他们不再受Hibernate管理。
  (1) 不再位于Session的缓存中，也可以说，游离对象不被Session关联。
  (2) 游离对象是由持久化对象转变过来的，因此在数据库中可能还存在与它对应的记录（前提条件是没有其他程序删除了这条记录）。
  ```

- Hibernate中对象状态转化图

  

###### 2.一级缓存

1. 为什么要用缓存？

   目的：减少对数据库的访问次数！从而提升hibernate的执行效率！

2. Hibernate中缓存的分类？

   Hibernate的一级缓存，二级缓存

3. 概念

   - **Hibenate中一级缓存，也叫做session的缓存，它可以在session范围内减少数据库的访问次数！ 只在session范围有效！ Session关闭，一级缓存失效！**
   - **当调用session的save/saveOrUpdate/get/load/list/iterator方法的时候，都会把对象放入session的缓存中。**
   - **Session的缓存由hibernate维护，** **用户不能操作缓存内容；** **如果想操作缓存内容，必须通过hibernate提供的evit/clear方法操作。**

4. session缓存的特点

   **只在(当前)session范围有效，作用时间短，效果不是特别明显！**

   **在短时间内多次操作数据库，效果比较明显！**

5. session缓存相关几个方法的作用

   ​		session.flush();    让一级缓存与数据库同步，批量操作时使用

   ​        session.clear();    清空一级缓存中缓存的所有对象，批量操作时使用

   ​		session.evict(arg0);  清空一级缓存中指定的对象

6. 问题1： 不同的session是否会共享缓存数据?

   ```Java
   不会
   User u1 = session1.get(User.class,1);  //把u1对象放入session1的缓存
   
   session2.update(u1);   //把u1放入session2的缓存
   
   u1.setName(‘new Name’);
   
   如果生成2条update sql， 说明不同的session使用不同的缓存区，不能共享。
   ```

7. 问题2：list与iterator查询的区别？

   ```Java
   list() 
   一次把所有的记录都查询出来，并且会放入缓存，但不会从缓存中获取数据；
   Iterator()
   N+1查询； N表示所有的记录总数
   即会先发送一条语句查询所有记录的主键（1），
   再根据每一个主键再去数据库查询（N）！
   会放入缓存，也会从缓存中取数据！
   ```

###### 3.懒加载

-  get、load方法区别？

  get: 及时加载，只要调用get方法立刻向数据库查询；

  load:默认使用懒加载，当用到数据的时候才向数据库查询；

-  懒加载：(lazy)

  概念：当用到数据的时候才向数据库查询，这就是hibernate的懒加载特性。

  目的：提供程序执行效率！

   lazy 值：

  ​     true  使用懒加载；

  ​     false  关闭懒加载；

  ​     extra  (集合数据懒加载时候提升效率)；

  注意：在真正使用数据的时候才向数据库发送查询的sql；

  如果调用集合的size()/isEmpty()方法，只是统计，不真正查询数据！

-  懒加载异常

   Session关闭后，不能使用懒加载数据；

  如果session关闭后，使用懒加载数据报错：

  org.hibernate.**LazyInitializationException**: could not initialize proxy - no Session

-  如何解决session关闭后不能使用懒加载数据的问题？

```Java
// 方式1： 先使用一下数据
dept.getDeptName();
// 方式2：强迫代理对象初始化
Hibernate.initialize(dept);
// 方式3：关闭懒加载
设置lazy=false;
//方式4：在使用数据之后，再关闭session 
```

###### 4.一对一映射

一对一映射是一对多映射的特列；在Hibernate中，处理一对一映射有两种配置方式：

- 第一种：基于外键的映射

  该映射主键一个字段，外键一个字段；

  ```xml
  <hibernate-mapping package="cn.itcast.c_one2one">
  	
  	<class name="IdCard" table="t_IdCard">
  		<id name="cardNum">
  			<generator class="assigned"></generator>
  		</id>	
  		<property name="place" length="20"></property>
  		<!-- 
  			一对一映射，有外键方
  			unique="true"   给外键字段添加唯一约束
  		 -->
  		 <many-to-one name="people" unique="true" column="people_id" class="People" cascade="save-update"></many-to-one>
  			
  	</class>
  </hibernate-mapping>
  
  ```

- 第二种：基于主键的映射

  该映射主键与外键是同一个字段

  ```xml
  <hibernate-mapping package="cn.itcast.c_one2one2">
  	
  	<class name="IdCard" table="t_IdCard">
  		<id name="people_id">
  			<!-- 
  				id 节点指定的是主键映射, 即user_id是主键
  				主键生成方式： foreign  即把别的表的主键作为当前表的主键；
  						property (关键字不能修改)指定引用的对象     对象的全名 bean.People、  对象映射 bean.People.hbm.xml、   table(id)
  			 -->
  			<generator class="foreign">
  				<param name="property">user</param>
  			</generator>
  		</id>	
  		<property name="cardNum" length="20"></property>
  		<property name="place" length="20"></property>
  		
  		<!-- 
  			一对一映射，有外键方
  			（基于主键的映射）
  			 constrained="true"  指定在主键上添加外键约束
  		 -->
  		<one-to-one name="user" class="User" constrained="true"  cascade="save-update"></one-to-one>
  			
  	</class>	
  
  </hibernate-mapping>
  
  ```

###### 5.组件映射与继承映射

- 类的关系

  ​     组合关系：  一个类中包含了另外一个类。这2个类中就是组合关系。

  ​     继承关系：  一个类继承另外一个类。这2个类中就是继承关系。

- 组件映射

  类组合关系的映射，也叫做组件映射。

  注意：组件类和被包含的组件类，共同映射到一张表！

  ```xml
  <hibernate-mapping package="bean">
  
      <class name="Car" table="t_car">
          <id name="id">
              <generator class="native"></generator>
          </id>
          <property name="name" length="20"></property>
  
          <!-- 组件映射 -->
          <component name="wheel">
              <property name="size"></property>
              <property name="count"></property>
          </component>
      </class>
  </hibernate-mapping>
  ```

- 继承映射

  ```xml
  简单继承映射：有多少个子类，写多少个映射文件！
  /**动物--猫（通过只配置猫的配置文件）--完成继承映射的目的*/
  <hibernate-mapping package="bean">
  
      <class name="Cat" table="t_Cat">
          <!-- 简单继承映射： 父类属性直接写 -->
          <id name="id">
              <generator class="native"></generator>
          </id>
          <property name="name"></property>
          <property name="catchMouse"></property>
      </class>
  </hibernate-mapping>
  ```

  ```xml
  继承映射2：JavaBean设计一样；所有子类映射到一张表 ，只用一个映射文件(1张表)基类表
  什么情况用？
  	子类教多，且子类较为简单，即只有个别属性！
  	好处：因为使用一个映射文件， 减少了映射文件的个数。
  	缺点：（不符合数据库设计原则）
  一个映射文件： Animal.hbm.xml
  <hibernate-mapping package="bean">
  
      <class name="Animal" table="t_animal">
          <id name="id">
              <generator class="native"></generator>
          </id>
          <!-- 指定鉴别器字段(区分不同的子类) -->
          <discriminator column="type_"></discriminator>
  
          <property name="name"></property>
  
          <!--
              子类：猫
                  每个子类都用subclass节点映射
                  注意：一定要指定鉴别器字段，否则报错！
                  鉴别器字段：作用是在数据库中区别每一个子类的信息， 就是一个列
              discriminator-value="cat_"
                  指定鉴别器字段,即type_字段的值
                  如果不指定，默认为当前子类的全名
           -->
          <subclass name="Cat" discriminator-value="cat_">
              <property name="catchMouse"></property>
          </subclass>
  
          <!--
              子类：猴子
           -->
          <subclass name="Monkey" discriminator-value="monkey_">
              <property name="eatBanana"></property>
          </subclass>
  
      </class>
  </hibernate-mapping>
  
  ```

  ```xml
  继承映射3：JavaBean设计一样；每个类映射一张表(3张表)；子类表引用父类的主键
  <hibernate-mapping package="bean">
  
      <class name="Animal2" table="t_animal2">
          <id name="id">
              <generator class="native"></generator>
          </id>
          <property name="name"></property>
  
          <!--
              子类：猫  t_cat
              key 指定_cat表的外键字段
          -->
          <joined-subclass name="Cat2" table="t_cat">
              <key column="t_animal_id"></key>
              <property name="catchMouse"></property>
          </joined-subclass>
  
          <!-- 子类：猴子  t_monkey -->
          <joined-subclass name="Monkey2" table="t_monkey">
              <key column="t_animal_id"></key>
              <property name="eatBanana"></property>
          </joined-subclass>
      </class>
  
  </hibernate-mapping>
  ```

  ```xml
  继承映射4：(推荐)每个子类映射一张表， 父类不对应表(共2张表);父类不在是抽象类；
  <!-- 
  	继承映射， 每个类对应一张表(父类不对应表)
   -->
  <hibernate-mapping package="bean">
  	<!-- 
  		 abstract="true"  指定实体类对象不对应表，即在数据库段不生成表
  	 -->
  	<class name="Animal" abstract="true">
  		<!-- 如果用union-subclass节点，主键生成策略不能为自增长！ -->
  		<id name="id">
  			<generator class="uuid"></generator>
  		</id>
  		<property name="name"></property>
  		
  		<!-- 
  			子类：猫  t_cat
  			union-subclass  
  				table 指定为表名, 表的主键即为id列
  		-->
  		<union-subclass name="Cat" table="t_cat">
  			<property name="catchMouse"></property>
  		</union-subclass>
  		
  		<!-- 子类：猴子  t_monkey -->
  		<union-subclass name="Monkey" table="t_monkey">
  			<property name="eatBanana"></property>
  		</union-subclass>
  		
  	</class>
  	
  
  </hibernate-mapping>
  ```

  



