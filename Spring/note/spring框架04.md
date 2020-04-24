###### 1. Spring与jdbc结合

jdbc编程不变，主要是Connection对象的维护，即配置并使用数据源.

```xml
<!-- 基于jdk的规范数据源 -->
<bean name="dataSource1"
class="oracle.jdbc.pool.OracleConnectionPoolDataSource">
		<property name="networkProtocol">
			<value>tcp</value>
		</property>
		<property name="databaseName">
			<value>XE</value>
		</property>
		<property name="driverType">
			<value>thin</value>
		</property>
		<property name="portNumber">
			<value>1521</value>
		</property>
		<property name="user">
			<value>root</value>
		</property>
		<property name="serverName">
			<value>127.0.0.1</value>
		</property>
		<property name="password">
			<value>root</value>
		</property>
</bean>
    
	注意:别忘了读取配置文件
	<!-- 读取这个资源文件 读完之后下面就可以用${key}来取文件中的value值了 -->
	<!-- 这种方式是我们第一节学习的那种配置方式方式的简写 -->
	<context:property-placeholder location="classpath:oracle.perperties"/>

2)<!-- dbcp数据源 -->
<bean id="dataSource2"
class="org.apache.commons.dbcp.BasicDataSource">
		<property name="driverClassName">
			<value>${driver}</value>
		</property>
		<property name="url">
			<value>${url}</value>
		</property>
		<property name="username">
			<value>${user}</value>
		</property>
		<property name="password">
			<value>${password}</value>
		</property>
		<!-- 最大连接数 -->
		<property name="maxActive">
			<value>80</value>
		</property>
		<!-- 最大空闲连接数 -->
		<property name="maxIdle">
			<value>20</value>
		</property>
		<!-- 最大等待时间:当没有可用连接时,连接池等待连接被归还的最大时间 单位:毫秒 -->
		<!-- 超过时间则抛出异常,如果设置为-1表示无限等待 -->
		<property name="maxWait">
			<value>3000</value>
		</property>
</bean>

3)<!-- spring提供的一种数据源 -->
<bean id="dataSource3"	class="org.springframework.jdbc.datasource.DriverManagerDataSource">
		<property name="driverClassName">
			<value>${driver}</value>
		</property>
		<property name="url">
			<value>${url}</value>
		</property>
		<property name="username">
			<value>${user}</value>
		</property>
		<property name="password">
			<value>${password}</value>
		</property>
</bean>
	
4)c3p0数据源
<!-- c3p0数据源 -->
<bean id="dataSource4" class="com.mchange.v2.c3p0.ComboPooledDataSource"
		destroy-method="close">
		<property name="driverClass">
			<value>${driver}</value>
		</property>
		<property name="jdbcUrl">
			<value>${url}</value>
		</property>
		<property name="user">
			<value>${user}</value>
		</property>
		<property name="password">
			<value>${password}</value>
		</property>

		<!--连接池中保留的最小连接数。 -->
		<property name="minPoolSize">
			<value>5</value>
		</property>

		<!--连接池中保留的最大连接数。Default: 15 -->
		<property name="maxPoolSize">
			<value>30</value>
		</property>

		<!--初始化时获取的连接数，取值应在minPoolSize与maxPoolSize之间。Default: 3 -->
		<property name="initialPoolSize">
			<value>10</value>
		</property>

		<!--最大空闲时间,60秒内未使用则连接被丢弃。若为0则永不丢弃。Default: 0 -->
		<property name="maxIdleTime">
			<value>60</value>
		</property>

		<!--当连接池中的连接耗尽的时候c3p0一次同时获取的连接数。Default: 3 -->
		<property name="acquireIncrement">
			<value>5</value>
		</property>

		<!--每60秒检查所有连接池中的空闲连接。Default: 0 -->
		<property name="idleConnectionTestPeriod">
			<value>60</value>
		</property>

		<!--定义在从数据库获取新连接失败后重复尝试的次数。Default: 30 -->
		<property name="acquireRetryAttempts">
			<value>30</value>
		</property>
</bean>
```

###### 2.spring与mybatis的整合

```xml
注意导入相关jar包:mybatis-spring-1.2.2.jar
使用Spring整合mybatis时,可以使用mybatis-config.xml文件,也可以不使用
   
<!-- 配置sqlSessionFactory 不使用mybatis-config.xml-->
<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource" />
        <property name="typeAliasesPackage" value="com.briup.db"></property>
        <property name="configurationProperties">
            <props>
                <prop key="cacheEnabled">true</prop> 
            </props>
        </property>
        <!-- 自动扫描mapping.xml文件 -->
        <property name="mapperLocations" value="classpath:db/mybatis/AccountMapper.xml" />
    </bean>	
        
	或者:

	<!-- 配置sqlSessionFactory 使用mybatis-config.xml-->
	<!-- 直接读取mybatis-config.xml文件,里面和之前配置的一样 -->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="configLocation"  value="classpath:mybatis-config.xml"/>
    </bean>

	
	最后还需要扫描mybatis中映射接口,以便spring为其生产对应的实现类
	<!-- 自动扫描映射接口所在的包 -->
    <!-- 将来可以通过接口的名字首字母小写作为beanName,从spring容器中拿出自动生成的该接口的实现类 -->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="db"/>
    </bean>
```

###### 3.spring的事务管理机制

```sql
1）编程式事务管理(不常用)
	所谓编程式事务指的是通过编码方式实现事务;

2）声明式事务管理(常用)
	在spring配置文件中声明式的处理事务来代替代码式的处理事务.
	在spring中,声明式事务主要是通过【事务属性】来定义的,事务属性描述了事务策略如何应用到方法上面
	
3）事务属性主要包含了以下5个方面:
	传播行为 (propagation):
	规定了如果有新的事务应该被启动还是被挂起,或者方法是否需要在事务中运行.
	TransactionDefinition.PROPAGATION_REQUIRED：如果当前存在事务，则加入该事务；如果当前没有事务，则创建一个新的事务.
	
	TransactionDefinition.PROPAGATION_REQUIRES_NEW：创建一个新的事务，如果当前存在事务，则把当前事务挂起.
	
	TransactionDefinition.PROPAGATION_SUPPORTS：如果当前存在事务，则加入该事务；如果当前没有事务，则以非事务的方式继续运行.
	TransactionDefinition.PROPAGATION_NOT_SUPPORTED：以非事务方式运行，如果当前存在事务，则把当前事务挂起.
	
	TransactionDefinition.PROPAGATION_NEVER：以非事务方式运行，如果当前存在事务，则抛出异常.
	
	TransactionDefinition.PROPAGATION_MANDATORY：如果当前存在事务，则加入该事务；如果当前没有事务，则抛出异常.
	
	TransactionDefinition.PROPAGATION_NESTED：如果当前存在事务，则创建一个事务作为当前事务的嵌套事务来运行；如果当前没有事务，则该取值等价于TransactionDefinition.PROPAGATION_REQUIRED.
	
	
    隔离级别 (isolation)：
    事务与事务之间的限定，或者说为避免事务之间引发的问题，引入隔离级别.

	脏读: 主要针对update操作.一个事务A读到另一个事务B中修改过但是还没有提交的数据
	
	不可重复读：主要针对update操作. 一个事务A在第一次读数据和
第二次读数据之间,有另一个事务B把这个数据更改并提交了,所有就出现了事务A里面读一个数据俩次,但是读到的结果是不同的.

 	 幻读:主要针对的是insert/delete操作.事务A第一次用where条件筛选出了10条数据,事务A第二次用通样的where条件筛选出的却是11条数据,因为事务B在事务A的第一次和第二次查询直接进行了插入操作,并且插入的这个数据满足事务A的where筛选条件.

	定义了一个事务可能受其他并发事务影响的程度。隔离级别是指若干个并发的事务之间的隔离程度。TransactionDefinition 接口中定义了五个表示隔离级别的常量：

	TransactionDefinition.ISOLATION_DEFAULT：这是默认值，表示使用底层数据库的默认隔离级别。对大部分数据库而言，通常这值为：TransactionDefinition.ISOLATION_READ_COMMITTED.	
TransactionDefinition.ISOLATION_READ_UNCOMMITTED：
	该隔离级别表示一个事务可以读取另一个事务修改但还没有提交的数据。该级别不能防止脏读和不可重复读，因此很少使用该隔离级别。不提交也能读
	
TransactionDefinition.ISOLATION_READ_COMMITTED：
该隔离级别表示一个事务只能读取另一个事务已经提交的数据。该级别可以防止脏读，这也是大多数情况下的推荐值。 提交之后才能读 解决了脏读.

TransactionDefinition.ISOLATION_REPEATABLE_READ：该隔离级别表示一个事务在整个过程中可以多次重复执行某个查询，并且每次返回的记录都相同。即使在多次查询之间有新增的数据满足该查询，这些新增的记录也会被忽略。该级别可以防止脏读和不可重复读。解决了脏读和不可重复读的问题.

TransactionDefinition.ISOLATION_SERIALIZABLE：所有的事务依次逐个执行，这样事务之间就完全不可能产生干扰，也就是说，该级别可以防止脏读、不可重复读以及幻读。但是这将严重影响程序的性能。通常情况下也不会用到该级别，该隔离级别，三个问题都解决了.

注意:不同的数据库所能支持的事务隔离级别以及默认的事务隔离级别有可能是不同的
    
    
    回滚规则 (rollback-for no-rollback-for):
    定义了哪些异常会导致事务回滚而哪些不会。默认情况下,事务在遇到运行时异常的时候才会回滚,而遇到检查时异常时不会回滚
	-Exception表示有Exception抛出时,事务回滚. -代表回滚+就代表提交.
	
	
    事务超时 (timeout):
    为了使应用程序可以很好的运行,事务不能运行太长的时间,所以这个属性就控制着这个时间.只有传播行为是PROPAGATION_REQUIRED
    、PROPAGATION_REQUIRES_NEW、PROPAGATION_NESTED的时候超时设置才有意义,因为超时时钟会在事务开始的时候启动,而在这三个传播行为下才有可能启动一个新事务.注意事务超时后会自动回滚.(单位是 秒).
	
	
    是否只读 (read-only):
    定义了一个事务中是否是只读操作,如果设置只读那么数据库内部就可以对该操作进行合适的优化措施,只有传播行为是PROPAGATION_REQUIRED、PROPAGATION_REQUIRES_NEW 、PROPAGATION_NESTED的时候只读设置才有意义,因为只读优化是在事务开始的时候由数据库实施的,而在这三个传播行为下才有可能启动一个新事务.
	
	
4）声明式事务管理的配置方式通常以下几种：
	注意:配置事务的方式都需要用到事务管理器(切面)和事务拦截器(advice),其实就是使用aop编程,把事务代码动态织入到需要使用的方法上.
	spring中实现aop的配置方式很多,但配置事务的时候推荐使用:
		1.tx前缀的事务标签和aop前缀的标签结合,将切面(事务管理器)织入到切入点上.
		2.注解进行事务配置.
		
```

