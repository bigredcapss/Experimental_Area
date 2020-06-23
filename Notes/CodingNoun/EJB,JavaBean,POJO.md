EJB,JavaBean,POJO

##### EJB

EJB：EJB是运行在J2EE服务器(WebLogic,JBoss等【Tomcat不算】)上的Java类，它用于处理业务逻辑的，应该是这样的：

　　　>有状态（Stateful）

　　　>无状态的(Stateless)

　　    >实体(Entity)

　　　>消息驱动Bean（Message Driven Beans）

```Java
//无状态Bean
@Stateless
public class EmployeeServiceBean {
    @PersistenceContext
    EntityManager em;
  
    public void addEmployee(Employee emp) {
        em.persist(emp);
    }
}
```

EJB是一种JavaBean的组合规范。可以认为它是一组"功能"JavaBean的集合。这里说EJB是一组JavaBean的意思 是这一组JavaBean组合起来实现了某个企业的业务逻辑。这里的一组JavaBean不是乱组合的，它们要能满足实现某项业务功能的。

在 J2EE里，Enterprise Java Beans(EJB)称为Java 企业Bean，是Java的核心代码，包括会话 Bean（Session Bean），实体Bean（Entity Bean）和消息驱动Bean（MessageDriven Bean）。
1.Session Bean用于实现业务逻辑。Session Bean可以直接访问数据库，但更多时候，它会通过Entity Bean实现数据访问。 这个类一般用单例模式来实现，因为每次连接都需要用到它。

2.Entity Bean是域模型对象，仅作为普通Java对象(POJO)来使用。用于实现O/R映射，负责将数据库中的表记录映射为内存中的Entity对象。

3.MessageDriven Bean是EJB2.0中引入的新的企业Bean，它基于JMS消息，只能接收客户端发送的JMS消息然后处理。MDB实际上是一个异步的无状态Session Bean，客户端调用MDB后无需等待，立刻返回，MDB将异步处理客户请求。这适合于需要异步处理请求的场合，比如订单处理，这样就能避免客户端长时间的等待一个方法调用直到返回结果。

##### JavaBean

JavaBean:([官方解释](https://docs.oracle.com/javase/tutorial/javabeans/))是可复用的Java组件，严格遵循Sun定义的规范要求,JavaBean是一个标准，开发者可以直接复用别人写好的软件组件而不必理解它内部的工作机制。简单来说一个JavaBean应该有下面几个特点，

　　>类应该是public的

　　>属性应该private的，对于属性值的访问应该是要通过getXX,setXX,isXXX方法，isXXX是用于检查元素的值是否是Boolean的。

　　>该类应该有一个无参的构造函数，元素值的初始化要通过setXXX方法。

　　>这个类应该是实现了Serializable 接口([`java.io.Externalizable`](https://docs.oracle.com/javase/8/docs/api/java/io/Externalizable.html))，这个是为了持久化存储的需要。

```Java
import java.io.Serializable;
  
public class Bar implements Serializable {
    private String name = null;
    private boolean flag = false;
    public Bar() {
    }
    public String getName() {
        return this.name;
    }
    public void setName(final String name) {
        this.name = name;
    }
    public boolean isFlag() {
        return this.flag;
    }
    public void setFlag(final boolean flag) {
        this.flag = flag;
    }
}
```

##### POJO

POJO:一个POJO没有要求去实现了一个接口或者继承一个类，也没有任何的指导信息。POJO最大的不同之处就是它和EJB无关。POJO是一个简化的JavaBean，我们之所以叫它是是简化的bean是因为它只用于装载数据而不用业务逻辑的处理。更没有被其他框架侵入的Java对象。POJO类只有一些private的参数作为对象的属性，然后针对每一个参数定义get和set方法访问的接口。一个持久化的POJO就是PO，如果用于展示层那么它就是VO 。

```Java
public class User {
	private String name;
	private String password;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
```

##### 扩展

```Java
1.PO(persistant object ):持久化对象
　　持久对象,可以看成是与数据库中的表相映射的java对象。最简单的PO就是对应数据库中某个表中的一条记录，多个记录可以用PO的集合。PO中应该不包含任何对数据库的操作。 

2.VO(Value Object)
　　一个值对象就是一个含有值的对象，比如java.lang.Integer.VO:通常用于业务层之间的数据传递，和PO一样也是仅仅包含数据而已。但应是抽象出的业务对象,可以和表对应,也可以不,这根据业务的需要.个人觉得同DTO(数据传输对象),在web上传递。

3.DAO:data access object
　　数据访问对象，是一个sun的一个标准j2ee设计模式 ．此对象用于访问数据库。通常和PO结合使用，DAO中包含了各种数据库的操作方法。通过它的方法,结合PO对数据库进行相关的操作。夹在业务逻辑与数据 库资源中间。配合VO, 提供数据库的CRUD操作。

4.DO（Domain Object）
　　领域对象，就是从现实世界中抽象出来的有形或无形的业务实体。

5.VO（View Object）
　　视图对象，用于展示层，它的作用是把某个指定页面（或组件）的所有数据封装起来。

6. BO( Business object)
　　用于调用DAO的业务逻辑类，并且将PO和VO联合起来进行业务操作。

7.DTO(Data Transfer Object)
　　主要用于远程调用中的传输对象。比如说，一个100个字段的表就对应于PO中的100个属性，但是我们的接口只需要10个字段。那么我们就可以将只含有十个字段的DTO传递给客户端使用。这不会向客户端暴露表结构，一旦它和接口关联起来，那么它就是VO了。
```

