#### 1.Spring相关概念

1. 轻量级的容器

   ```
   容器：spring容器帮我们管理业务逻辑层，里边有很多业务逻辑对象，有对象就有对象的生命周期的管理（创建，销毁）；
   
   轻量级：容器为业务逻辑对象提供多种服务？spring给开发者提供的服务完全由开发者自己决定，想用什么服务自己开启使用。但是重量级的都是只要你用就把所有的服务都给你，不能自己定制。spring容器从来不能独立运行，一定借助于其他容器启动，或者借助web容器启动，或者ejb容器启动；
   
   特点：应用模块之间耦合度小，组件都是可重用的，都是各自打包的；
   ```

2. why spring?

   ```
   1）动态解藕，方便开发，面向接口设计
   通过Spring提供的IoC容器，我们可以将对象之间的依赖关系交由Spring进行控制，避免硬编码所造成的过度程序耦合。有了Spring，用户不必再为单实例模式类、属性文件解析等这些很底层的需求编写代码，可以更专注于上层的应用;
   
   2）方便程序的测试TDD（Test-Driven Development）
   可以用非容器依赖的编程方式进行几乎所有的测试工作，在Spring里，测试不再是昂贵的操作，而是随手可做的事情;
   
   3)降低Java EE API的使用难度
   Spring对很多难用的Java EE API（如JDBC，JavaMail，远程调用等）提供了一个简单的封装层，通过Spring的简易封装，这些Java EE API的使用难度大为降低;
   
   4）方便集成各种优秀框架
   Spring不排斥各种优秀的开源框架，相反，Spring可以降低各种框架的使用难度，Spring提供了对各种优秀框架（如Struts,mybatis,Hibernate、Hessian、Quartz）等的直接支持;
   
   5）AOP编程的支持
   通过Spring提供的AOP功能，方便进行面向切面的编程，许多不容易用传统OOP实现的功能可以通过AOP轻松应付;
   
   6）声明式事务的支持
   在Spring中，我们可以从单调烦闷的事务管理代码中解脱出来，通过声明式方式灵活地进行事务的管理，提高开发效率和质量;
   
   7）对异常的处理方式，所有的都转换成Unchecked的;
   
   8）它不是一个一体化的解决方案。;
   
   9）良好的设计，容易扩展，很多可重用的组件;
   
   ```

3. spring核心组件

   ```
   1）Spring Core(IOC)： 核心容器，提供组件的创建、装备、销毁；
   
   2）Spring Context： Spring上下文，是接口ApplicationContext（继承自BeanFactory接口）的实现；
   
   3）Spring Web容器：web应用上下文，是webApplicationContext接口的实现；
   
   4）SpringDAO容器：是SpringDAO 支持模块，是为了简化DAO的使用；
   5）SpringORM 
   
   6）Spring AOP ：对AOP编程支持的模块；
   
   7）Spring MVC：控制层的一个框架；
   ```

4. spring ioc（Inversion of Control） 控制反转

   ```
   说法1：对象之间的依赖关系，由容器在运行时依据配置文件动态的建立；
   说法2：对象的控制权转移了，转到外部容器了，避免了代码的纠缠，代码更容易被维护，模板之间的耦合性降低，容易测试；
   
   我的想法：IoC 控制反转意味着将你设计好的类交给容器去控制,而不是在类的内部进行控制,即控制权由应用代码中转到了外部容器；
   
   IoC包括两部分内容：
         DI（Dependency Injection）依赖注入：组件不做定位查询,只提供相应方法,由容器创建对象,并调用相应方法设置所需对象需要的组件；
         DL（Dependency Lookup）依赖查找：容器创建对象并提供回调接口和上下文环境给组件,需要时通过接口从容器中查找对象；依赖查找，现在使用不太多。（EJB使用的更多，将对象创建好后，放到容器中）
        
   IOC解决的问题：对象谁来创建的问题；
   DI解决的问题：对象间的关系如何建立的问题；
   org.springframework.beans包及org.springframework.context包是IOC容器的基础；
   ```

5. spring ioc核心api

   ```
   BeanFactory接口和容器:
      BeanFactory是Spring中Bean容器,IoC的核心接口,主要用于处理Bean的初始化和配置,建立对象间的依赖关系;
      
   ApplicationContext接口
      该接口继承于BeanFactory,增强了BeanFactory,提供了事务处理AOP,国际化,事件传递等;
      
   定义了如下方法:
   Object getBean(String name)//根据指定名称返回一个Bean实例
   
   <T> T getBean(Class<T> requiredType)//返回一个与给定Class唯一匹配的Bean实例
   
   <T> T getBean(String name, Class<T> requiredType)
   
   Object getBean(String name, Object... args)
   
   Class<?> getType(String name)//得到名称为name的Bean的Class对象
      
   boolean isPrototype(String name)//判断名称为name的Bean是否是原型,即是否总是返回一个新实例
   
   boolean isSingleton(String name)//判断名称为name的Bean是否是单例
   
   boolean containsBean(String name)//判断是否包含给定名称的Bean实例
   
   boolean isTypeMatch(String name, Class<?> targetType)//判断名称为name的Bean实例是否为targetType类型
   
   String[] getAliases(String name)//如果名称为name的Bean有别名返回

   注意：通过getBean方法便可以得到相应的类实例,但是最好永远不调用,而使用注解注入,避免对Spring API的依赖
   ```
   
6. 配置文件

   ```xml
   spring bean配置：
   通过读取xml文件配置元数据，来对应用中各个对象进行实例化配置以及组装,通常使用xml文件来作为配置元数据的描述格式，可以将xml配置分别写在多个文件中；
   
   可以将多个配置放在一个String数组中传递给容器进行初始化，eg: ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]
   {"services.xml","daos.xml"})
   
   也可以在XML中使用<import resource="" />来进行导入
   
   XML基本结构如下:
      <?xml version="1.0" encoding="UTF-8"?>
      <beans xmlns=".....">
         <bean id=".." class="..." >
            <property name="..." ... />
         </bean>
         ...
      </beans>
   
   容器的初始化和ClassPathXmlApplicationContext: ClassPathXmlApplication实现ApplicationContext,用于读取Xml初始化上下文,初始化方法如下:
   ApplicationContext ac = new ClassPathXmlApplicationContext(".../path/beans.xml");
   与Hibernate类似,不同的是Spring没有默认的配置文件
   ```

7. ioc 注入

   ```Java
   1）setter方式（必须依靠无参构造器）
   A.基本类型（8中基本类型+字符串）的装配
   注意： 用setter方式注入的话，必须要用set、get方法;
   方式: 配置元素<value/>
   e.g:
   HelloBean.java:
   public class HelloBean 
   {
       private String name;
       private int age;
       public String sayHello(){
       return "hello "+name +",your age is" + age;
       }
       ...
   }
   
   配置文件applicationContext.xml:
   <!--id属性：id是Bean的唯一标识，要求在整个配置文件中要唯一，也可使用name属性，bean标签里面的id和name属性都可以用来标识这个配置的对象-->
       
   <!--property标签： 对于所有用setter方式注入的对象属性必须用Property来指定-->
       
   <!--value标签/属性： 是对以基本类型的值，都用value来注入，可以实现自动的数据类型转换-->
   
   <bean id="helloBean" class="ioc.HelloBean">
   <property name="name"><value>terry</value>
   </property>
   <property name="age" value="20"></property>
   </bean>
   
   测试类：
   public class Test {
   	public static void main(String[] args) {
   		ApplicationContext ac = new 			ClassPathXmlApplicationContext("ioc/applicationContext.xml");
   		//获取spring容器中的一个实例
   		HelloBean hb = (HelloBean)
   ac.getBean("helloBean");
   		System.out.println(hb.sayHello());
   	}
   }
   
   B.引用类型的装配
   ①<ref local=" "/> 
   ②<ref bean=" "/> 
   ③使用property的ref属性引用
   e.g:
   OtherBean.java:
   public class OtherBean {
   	private String str1;
   	public String getStr1() {
   		return str1;
   	}
   	public void setStr1(String str1) {
   		this.str1 = str1;
   	}
   	public String toString(){
   		return "OtherBean "+str1;
   	}
   }
   SomeBean.java:
   public class SomeBean {
   	private OtherBean ob;
   	public void printInfo(){
   		System.out.println("someBean "+ob);
   	}
   	public OtherBean getOb() {
   		return ob;
   	}
   	public void setOb(OtherBean ob) {
   		this.ob = ob;
   	}
   }
   
   配置applicationContext.xml:
   <bean id="someBean" class="ioc.SomeBean">
   <property name="ob"><ref bean="otherBean"/>
   </property>
   </bean>
   
   配置other.xml文件
   <bean id="otherBean" class="ioc2.OtherBean">
   <property name="str1"><value>string1</value>
   </property>
   </bean>
   
   测试类：
   public static void main(String[] args) {
   	ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"ioc2//applicationContext.xml","ioc2//other.xml"});
   	SomeBean sb =(SomeBean)ac.getBean("someBean");
   	sb.printInfo();
   }
   
   C.集合的装配
   方式：配置元素<list> <set> <map> <props>
   SomeBean.java
   public class SomeBean {
   	private List listProperty;
   	private Set setProperty;
   	private Map mapProperty;
   	private Properties<String,String> property;
   	public List getListProperty() {
   		return listProperty;
   	}
   	public void setListProperty(List 
   	listProperty){
   		this.listProperty = listProperty;
   	}
   	public Set getSetProperty() {
   		return setProperty;
   	}
   	public void setSetProperty(Set setProperty) {
   		this.setProperty = setProperty;
   	}
   	public Map getMapProperty() {
   		return mapProperty;
   	}
   	public void setMapProperty(Map mapProperty) {
   		this.mapProperty = mapProperty;
   	}
   	public Properties getProperty() {
   		return property;
   	}
   	public void setProperty(Properties property) {
   		this.property = property;
   	}
   	public void printInfo(){
   		System.out.println("listProperty");
   		System.out.println(listProperty);
   		System.out.println("setProperty");
   		System.out.println(setProperty);
   		Set set = mapProperty.entrySet();
   		Iterator it = set.iterator();
   		while(it.hasNext()){
    			Map.Entry entry = (Entry) it.next()	 			 System.out.println("Key"
    +entry.getKey());
    			System.out.println("value"
    +entry.getValue());
   		}
   		System.out.println("props: ");
   		Set set2 = property.entrySet();
   		Iterator it2 = set2.iterator();
   		while(it2.hasNext()){
   			Map.Entry entry= (Entry) it2.next();
   			System.out.println("key"
   +entry.getKey());
   			System.out.println("value"
   +entry.getValue());
   		}
   	}
   }
   
   配置文件applcationContext.xml：
   <bean id="someBean" class="ioc.SomeBean">
   <property name="listProperty">
    <list>
       <value>list1</value>
       <value>list1</value>
       <value>list3</value>
    </list>
   </property>
   <property name="setProperty">
    <set>
       <value>set1</value>
       <value>set1</value>
       <value>set3</value>
    </set>
   </property>
   <property name="mapProperty">
    <map>
       <entry key="key1">
             <value>value1</value>
       </entry>
      <entry key="key2">
             <value>value2</value>
       </entry>
    </map>
   </property>
   <property name="property">
    <props>
     	<prop key="key1">prop1</prop>
     	<prop key="key2">prop2</prop>
     	<prop key="key3">prop3</prop>
    </props>
   </property>
   </bean>
   
   测试类：
   public static void main(String[] args) {
   	ApplicationContext  ac = new 
   ClassPathXmlApplicationContext("ioc/aplicationContext.xml");
       SomeBean sb = (SomeBean) ac.getBean
       ("someBean");
       sb.printInfo();
   }
   
   
   2）基于构造器
   方式: 配置<constructor-arg>元素，Bean中不用写set方法，但是要有相应的构造器；
   构造器重载：参数的个数，类型，顺序
   <constructor-arg type="int" value="">
   <constructor-arg  index="0" value="">
   e.g:
   SomeBean.java:
   public class SomeBean {
       private String str1;
       private String str2;
       private int value1;
   	public SomeBean(String str1, String str2, int value1) {
   		super();
   		this.str1 = str1;
   		this.str2 = str2;
   		this.value1 = value1;
   	}
   	public void printInfo(){
      		System.out.println("str1 "+str1 +"str2 "+str2+" value1 "+value1 );
   	}
   }
   
   配置文件applicationContext.xml:
   <bean id="someBean" class="ioc.SomeBean">
   <!-- 方式一：使用类型注入 -->
   <constructor-arg type="java.lang.String">
     <value>String1</value>
   </constructor-arg>
   <constructor-arg type="java.lang.String" value="String2">
   </constructor-arg>
   <constructor-arg type="int">
     <value>100</value>
   </constructor-arg>
   <!-- 方式二：使用参数的索引注入 -->
   <constructor-arg index="1"> <!--表示第二个参数-->
     <value>String1</value>
   </constructor-arg>
   <constructor-arg index="0">
   </constructor-arg>
   <constructor-arg index="2">
     <value>100</value>
   </constructor-arg>
   </bean>
   
   测试类：
   public static void main(String[] args) {
   	ApplicationContext  ac = new 
   ClassPathXmlApplicationContext("ioc/aplicationContext.xml");
       SomeBean sb = (SomeBean) ac.getBean
       ("someBean");
       sb.printInfo();
   }
   
   
   3)自动装配（依照一些规则去装配bean中的属性）
   在beans标签中配置装载方式：default-autowire="byName"
   或者在bean标签中指定配置方式;
   
   A:autowire="byName":
   spring容器会到当前的类中找property的名字,然后再根据这个名
   字去spring容器中找有没有和这个property名字相同的对象,有的话,就把这个对象当做参数放到setXxx这个方法里面注入进来.
   property指的类中的属性；
   
   B:autowire="byType":
   spring容器会根据当前类中的set方法里面参数的类型,去容器中找相匹配的对象,如果没找到就算了,如果找到一个就注入进来,如果找到多个,那么就会报错了；
       
   C：autoWirde = "constructor"：
   根据构造器的参数类型去匹配，实现自动装配；
   
   D:autoWire = "autoDetect":
   自动检测,spring 3.0配置的xml不能使用autodetect，spring3.0应该是去掉了这个功能，改用spring2.5配置可以顺利通过测试；首先使用construct的自动装配形式进行装配，如果没有construct就通过byType的形式进行自动装配；
   
   E:默认的方式是不进行自动装配，通过手工设置ref 属性来进行装配bean;
   
   SomeBean.java
   public class SomeBean {
       private String str2;
       private OtherBean ob;
       public SomeBean(OtherBean ob) {
       	super();
       	this.ob = ob;
       }
       public String getStr2() {
       	return str2;
       }
       public void setStr2(String str2) {
       	this.str2 = str2;
   	}
   	public OtherBean getOb() {
   		return ob;
   	}
   	public void setOb(OtherBean ob) {
   		this.ob = ob;
   	}
   	public void printInfo(){
   		System.out.println("str2 "+str2 +" ob "+ob);
   	}
   }
   
   OtherBean.java:
   public class OtherBean {
       private String str1;
       public String getStr1() {
       	return str1;
       }
       public void setStr1(String str1) {
       	this.str1 = str1;
       }
       public String toString() {
       	return "str1 "+str1;
       }  
   }
   
   applicationContext.xml文件：
   注意：自动装配的优先级低于手动装配，动装配一般用于快速开发建立系统原型的情况，但是在正式的开发中很少使用，因为容易出错，难以维护；
   <bean id="otherBean" class="ioc.OtherBean">
   <property name="str1" value="String1" />
   </bean>
   <!-- 
       
   <bean id="someBean" class="ioc.SomeBean" autowire="byName">
   <property name="str2" value="String2" />
   </bean>
   
    -->
   <bean id="someBean" class="ioc.SomeBean" autowire="byType">
   <property name="str2" value="String2" />
   </bean>
   
   <bean id="someBean" class="ioc.SomeBean" autowire="constructor">
   <property name="str2" value="String2" />
   </bean>
   
   
   4)继承装入: 并不是面向对象开发中的继承关系
   继承装入，指bean的配置可以继承；
   在bean标签中配置abstract属性为”true”,表示抽象化,则spring容器不会为该类创建对象，spring默认abstract = false；在bean标签中配置parent属性，则可以让该bean继承父类属性的值，parent = "父类bean的id"；
   
   e.g
Car.java:
   public class Car {
       private String owner;
       private String name;
       private int price;
       public String getOwner() {
       	return owner;
       }
       public void setOwner(String owner) {
       	this.owner = owner;
       }
       public String getName() {
       	return name;
       }
       public void setName(String name) {
       	this.name = name;
       }
       public int getPrice() {
       	return price;
       }
       public void setPrice(int price) {
       	this.price = price;
       }
       public String toString() {
   		return owner+" "+name+" "+price;
   	}
   }
   
   applicationContext.xml文件：
   <bean id="abstractCar" class="ioc.Car" abstract="true">
   <property name="owner" value="zwb" />
   </bean>
   <bean id="car1" parent="abstractCar">
   <property name="name" value="qq" />
   <property name="price" value="10" />
   </bean>
   <bean id="car2" parent="abstractCar">
   <property name="name" value="baoma" />
   <property name="price" value="70" />
   </bean>
   
   测试类：
   public class Test {
   	public static void main(String[] args){
   		ApplicationContext ac = new ClassPathXmlApplicationContext("ioc/applicationContext.xml");
       Car car1=(Car) ac.getBean("car1");
       Car car2=(Car) ac.getBean("car2");
       System.out.println(car1.toString());
       System.out.println(car2.toString());
       }
   }
   
   
   5）注解注入：推荐使用，暂不解释；
   ```
   
8. 创建Bean实例的方式

   ```java
   1）通过反射调用构造方法创建bean对象
   调用类的构造方法获取对应的bean实例，是使用最多的方式，这种方式只需要在xml bean标签中指定class属性，spring容器内部会自动调用该类型的构造方法来创建bean对象，将其放在容器中以供使用；
   constructor-arg:用于指定构造方法参数的值;
   index：构造方法中参数的位置，从0开始，依次递增;
   value：指定参数的值;
   ref：当插入的值为容器内其他bean的时候，这个值为容器中对应bean的名称;
   
   e.g:
   applicationContext.xml文件：
   <bean id="bean名称" name="bean名称或者别名" class="bean的完整类型名称">
       <constructor-arg index="0" value="bean的值" ref="引用的bean名称" />
       <constructor-arg index="1" value="bean的值" ref="引用的bean名称" />
       <constructor-arg index="2" value="bean的值" ref="引用的bean名称" />
       ....
       <constructor-arg index="n" value="bean的值" ref="引用的bean名称" />
   </bean>
   
   
   2）通过静态工厂方法创建bean对象
   创建静态工厂，内部提供一些静态方法来生成所需要的对象，将这些静态方法创建的对象交给spring以供使用,工厂类不会被实例化,各个bean在创建之前也可以进行相同的初始化处理；
   class：指定静态工厂完整的类名；
   factory-method：静态工厂中的静态方法，返回需要的对象；
   constructor-arg：用于指定静态方法参数的值，用法和上面介绍的构造方法一样；
   
   e.g
   applicationContext.xml文件：
   spring容器会自动调用静态工厂的静态方法获取指定的对象，将其放在容器中以供使用；
   <bean id="bean名称" name="" class="静态工厂完整类名" factory-method="静态工厂的方法">
       <constructor-arg index="0" value="bean的值" ref="引用的bean名称" />
       <constructor-arg index="1" value="bean的值" ref="引用的bean名称" />
       <constructor-arg index="2" value="bean的值" ref="引用的bean名称" />
       ....
       <constructor-arg index="n" value="bean的值" ref="引用的bean名称" />
   </bean>
   
   
   3）通过实例工厂方法创建bean对象
   让spring容器去调用某些对象的某些实例方法来生成bean对象放在容器中以供使用；
   
   e.g
   applicationContext.xml文件：
   spring容器以factory-bean的值为bean名称查找对应的bean对象，然后调用该对象中factory-method属性值指定的方法，将这个方法返回的对象作为当前bean对象放在容器中供使用。
   <bean id="bean名称" factory-bean="需要调用的实例对象bean名称" factory-method="bean对象中的方法">
       <constructor-arg index="0" value="bean的值" ref="引用的bean名称" />
       <constructor-arg index="1" value="bean的值" ref="引用的bean名称" />
       <constructor-arg index="2" value="bean的值" ref="引用的bean名称" />
       ....
       <constructor-arg index="n" value="bean的值" ref="引用的bean名称" />
   </bean>
   
   
   4）通过FactoryBean创建bean对象
   前面了解了BeanFactory接口，BeanFactory是spring容器的顶层接口，而这里要说的是FactoryBean，也是spring提供的一个接口，这两个接口很容易搞混淆，FactoryBean可以让spring容器通过这个接口的实现来创建我们需要的bean对象；
   
   FactoryBean接口源码：
   public interface FactoryBean<T> {
   
       /**
        * 返回创建好的对象
        */
       @Nullable
       T getObject() throws Exception;
   
       /**
        * 返回需要创建的对象的类型
        */
       @Nullable
       Class<?> getObjectType();
   
       /**
       * bean是否是单例的
       **/
       default boolean isSingleton() {
           return true;
       }
   
   }
   
   解释：接口中有3个方法，前面2个方法需要我们去实现，getObject方法内部由开发者自己去实现对象的创建，然后将创建好的对象返回给spring容器，getObjectType需要指定我们创建的bean的类型；最后一个方法isSingleton表示通过这个接口创建的对象是否是单例的，如果返回false，那么每次从容器中获取对象的时候都会调用这个接口的getObject() 去生成bean对象。
   
   applicationContext.xml文件写法：
   <bean id="bean名称" class="FactoryBean接口实现类" />
       
   e.g
   自定义FactoryBean实现类UserFactoryBean.java:
   @1：返回了一个创建好的UserModel对象;
   @2：返回对象的Class对象;
   @3：返回true，表示创建的对象是单例的，那么我们每次从容器中获取这个对象的时候都是同一个对象;
   @4：此处用到了一个count，通过这个一会可以看出isSingleton不同返回值的时候从容器获取的bean是否是同一个;
   public class UserFactoryBean implements FactoryBean<UserModel> {
       int count = 1;
       @Nullable
       @Override
       public UserModel getObject() throws Exception { //@1
           UserModel userModel = new UserModel();
           userModel.setName("我是通过FactoryBean创建的第"+count+++ "对象");//@4
           return userModel;
       }
   
       @Nullable
       @Override
       public Class<?> getObjectType() {
           return UserModel.class; //@2
       }
   
       @Override
       public boolean isSingleton() { 
           return true; //@3
       }
   }
   
   applicationContext.xml文件：
   <!-- 通过FactoryBean 创建UserModel对象 -->
   <bean id="createByFactoryBean" class="ioc.UserFactoryBean"/>
   
   
   测试类：
   public class Client {
       public static void main(String[] args) {
           //1.bean配置文件位置
           String beanXml = "ioc/applicationContext.xml";
   
           //2.创建ClassPathXmlApplicationContext容器，给容器指定需要加载的bean配置文件
           ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(beanXml);
   
           System.out.println("spring容器中所有bean如下：");
   
           //getBeanDefinitionNames用于获取容器中所有bean的名称
           for (String beanName : context.getBeanDefinitionNames()) {
               System.out.println(beanName + ":" + context.getBean(beanName));
           }
   
           System.out.println("--------------------------");
           //多次获取createByFactoryBean看看是否是同一个对象
           System.out.println("createByFactoryBean:" + context.getBean("createByFactoryBean"));
           System.out.println("createByFactoryBean:" + context.getBean("createByFactoryBean"));
       }
   }   
   ```

   关于spring 创建bean实例的方式可参考  [spring 创建bean实例的方式](https://cloud.tencent.com/developer/article/1585379)

9. spring bean的生命周期

   ```
   在ApplicationContext中bean的生命周期
   
   生命周期执行的过程如下：
   1）需找所有的bean根据bean定义的信息来实例化bean
   默认bean都是单例;
   2）使用依赖注入，spring按bean定义信息配置bean的所有属性;
   3)若bean实现了BeanNameAware接口，工厂调用Bean的setBeanName（）方法传递bean的ID;
   4）若bean实现了BeanFactoryAware接口，工厂调用setBeanFactory（） 方法传入工厂自身;
   5）若bean实现了ApplicationContextAware()接口，setApplicationContext()方法会被调用;
   6)若bean实现了InitializingBean，则
   afterPropertiesSet被调用;
   7）若bean指定了init-method="init"方法，它将被调用;
   8）若BeanPostProcessor和bean关联,
   则它们的postProcessBeforeInitialization()方法被调用;
   9）若有BeanPostProcessor和bean关联，则它们的postProcessAfterInitialization()方法被调用;
   注意：通过已上操作,此时的Bean就可以被应用的系统使用，并将保留在BeanFactory工厂中直到不再需要为止.但我们也可以通过10）或者11）进行销毁；
   10）若bean实现了DisposableBean接口,distroy()方法被调用；
   11）如果指定了destroy-method="close"定制的销毁方法，就调用这个方法；
   ```

   关于spring中bean的生命周期可参考：[spring bean的生命周期](http://www.cnblogs.com/zrtqsk/p/3735273.html)

