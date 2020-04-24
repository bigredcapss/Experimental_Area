###### 1.Hibernate的几种查询方式

```Java
//主键查询
Dept dept =  (Dept) session.get(Dept.class, 12);
Dept dept =  (Dept) session.load(Dept.class, 12);

//HQL查询
Query q = session.CreateQuery("from Employee");
q.list();

// Criteria查询
Criteria criteria = session.CreateCriteria(Employee.class);
// 构建条件
criteria.add(Restrictions.eq("empId", 12));
criteria.list();

// 本地SQL查询
SQLQuery q = session.createSQLQuery("SELECT * FROM t_Dept limit 5;").addEntity(Dept.class); 
q.list();

// 分页查询
Query q = session.createQuery("from Employee");
// 总记录数
ScrollableResults scroll = q.scroll();  // 得到滚动的结果集
scroll.last();							//  滚动到最后一行
int totalCount = scroll.getRowNumber() + 1;// 得到滚到的（总的）记录数	 
// 设置分页参数
q.setFirstResult(0);
q.setMaxResults(3);

```

###### 2.Hibernate对连接池的支持

连接池作用就是管理连接，提升连接的利用效率！常用的连接池有 C3P0连接池、Druid(流行)；Hibernate自带的也有一个连接池，且对C3P0连接池也有支持！Hibernate自带的连接池：只维护一个连接，比较简陋；可以查看hibernate.properties文件查看连接池详细配置；

```xml
#################################
### Hibernate Connection Pool ###     
#################################

hibernate.connection.pool_size 1 【Hbm 自带连接池： 只有一个连接】

###########################
### C3P0 Connection Pool###		   【Hbm对C3P0连接池支持】
###########################

#hibernate.c3p0.max_size 2				最大连接数
#hibernate.c3p0.min_size 2				最小连接数
#hibernate.c3p0.timeout 5000           超时时间
#hibernate.c3p0.max_statements 100     最大执行的命令的个数
#hibernate.c3p0.idle_test_period 3000    空闲测试时间
#hibernate.c3p0.acquire_increment 2     连接不够用的时候， 每次增加的连接数
#hibernate.c3p0.validate false

【Hbm对C3P0连接池支持，  核心类】
告诉hib使用的是哪一个连接池技术。
#hibernate.connection.provider_class org.hibernate.connection.C3P0ConnectionProvider

```

###### 3.Hibernate的二级缓存

- 二级缓存的概念

  二级缓存：Hibernate提供了基于应用程序级别的缓存， 可以跨多个session，即不同的session都可以访问缓存数据。 这个换存也叫二级缓存。Hibernate提供的二级缓存有默认的实现，且是一种可插配的缓存框架！如果用户想用二级缓存，只需要在hibernate.cfg.xml中配置即可； 不想用，直接移除，不影响代码。如果用户觉得hibernate提供的框架框架不好用，自己可以换其他的缓存框架或自己实现缓存框架都可以。

- 二级缓存的使用

  二级缓存，使用步骤：

  1) 开启二级缓存

  2)指定缓存框架

  3)指定那些类加入二级缓存

  4)测试二级缓存！

  查看hibernate.properties配置文件，二级缓存如何配置？

  ```xml
  ##########################
  ### Second-level Cache ###
  ##########################
  #hibernate.cache.use_second_level_cache false【二级缓存默认不开启，需要手动开启】
  #hibernate.cache.use_query_cache true   【开启查询缓存】
  ## choose a cache implementation   【二级缓存框架的实现】
  
  #hibernate.cache.provider_class org.hibernate.cache.EhCacheProvider
  #hibernate.cache.provider_class org.hibernate.cache.EmptyCacheProvider
  
  hibernate.cache.provider_class org.hibernate.cache.HashtableCacheProvider 默认实现
  
  #hibernate.cache.provider_class org.hibernate.cache.TreeCacheProvider
  
  #hibernate.cache.provider_class org.hibernate.cache.OSCacheProvider
  
  #hibernate.cache.provider_class org.hibernate.cache.SwarmCacheProvider
  ```

- 缓存策略

  ```Xml
  <class-cache usage="read-only"/>放入二级缓存的对象，只读; 
  <class-cache usage="nonstrict-read-write"/> 非严格的读写
  <class-cache usage="read-write"/>  读写； 放入二级缓存的对象可以读、写；
  <class-cache usage="transactional"/>  (基于事务的策略)
  ```

- 集合缓存

  ```xml
  <!-- 集合缓存[集合缓存的元素对象，也加加入二级缓存] -->
  <collection-cache usage="read-write" collection="bean.Dept.emps"/>
  ```

- 查询缓存

  list() 默认情况只会放入缓存，不会从一级缓存中取！

  使用查询缓存，可以让list()查询从二级缓存中取！

###### 4.Hibernate中session的管理方式

```Java
Session session = sf.openSession();//每次都创建一个新的session 对象
Session session = sf.getCurrentSession()//session绑定线程
```

```Java
//openSession:  创建Session, 每次都会创建一个新的session
Session session1 = sf.openSession();
Session session2 = sf.openSession();
System.out.println(session1 == session2);
session1.close();
session2.close();

//getCurrentSession 创建或者获取session
// 线程的方式创建session
// 一定要配置：<property name="hibernate.current_session_context_class">thread</property>
Session session3 = sf.getCurrentSession();// 创建session，绑定到线程
Session session4 = sf.getCurrentSession();// 从当前访问线程获取session
System.out.println(session3 == session4);

// 关闭 【以线程方式创建的session，可以不用关闭； 线程结束session自动关闭】
session3.close();
//session4.close(); //报错，因为同一个session已经关闭了！
```

