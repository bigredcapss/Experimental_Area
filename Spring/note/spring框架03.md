###### 1.代理模式相关概念

	代理模式的特征是代理类与委托类有同样的接口(一般情况下)；
	
	代理类主要负责给委托类预处理消息、过滤消息、把消息转发给委托类,以及事后处理消息等；
	
	代理类与委托类之间通常会存在关联关系,一个代理类的对象与一个委托类的对象关联；
	
	代理类的对象本身并不真正实现功能,而是通过调用委托类的对象的相关方法,来提供特定的功能；
	
	注意:
		委托类对象就是我们后面说到的"目标对象", 也就是需要【被】代理的对象 target；
		代理类对象就是我们后面说到的"代理对象",目标对象就是需要这个对象为其做为代理 proxy；
	
	按照代理类的创建时期,代理类可分为两种。
		静态代理类:
			在程序运行前,代理类的.class文件就已经存在了；
		动态代理类:
			在程序运行时,代理类是运用了反射技术或字节码技术动态创建而成的；

######  2. 静态代理

```Java
接口:	HelloService
委托类:HelloServiceImpl
代理类:HelloServiceProxy

	public interface HelloService{
		public String sayHello(String msg);
	}

	public class HelloServiceImpl implements HelloService{
		public String sayHello(String msg){
			return "hi!"+msg;
		}
	}

	public class HelloServiceProxy implements HelloService{
		//目标对象
		private HelloService target; 

		//构造器中接收目标对象
		public HelloServiceProxy(HelloService target)		{
			this.target=target;
	   }

		public String sayHello(String msg){
			System.out.println("目标方法调用前"); 

			//调用目标对象的方法
			//这个方法才是我们真正要执行的方法
			String result = target.sayHello(msg); 

			System.out.println("目标方法调用后"); 

			return result;
		}

	}

	main:
        HelloService target = 
            new HelloServiceImpl();

        HelloService proxy = 
            new HelloServiceProxy(target);

        System.out.println(proxy.sayHello("hello"));
```

###### 3.动态代理（JDK动态代理）

动态代理类的字节码在程序运行时利用Java的反射机制动态生成,无需程序员手工编写它的源代码，动态代理类不仅简化了编程工作,而且提高了软件系统的可扩展性,因为使用它可以生成任意类型的动态代理类；java.lang.reflect包下面的Proxy类和InvocationHandler接口提供了生成动态代理类的能力；

```Java

public interface IStudentService {
	void save(Student s);
	void delete(long id);
	Student find(long id);
}
   
public class StudentServiceImpl implements IStudentService {
	public void delete(long id) {
		// 记录日志
		System.out.println("student is deleted...");
	}

	public Student find(long id) {
		// 记录日志
		System.out.println("student is found...");
		return null;
	}

	public void save(Student s) {
		// 记录日志
		System.out.println("student is saved...");
	}
}

日志类:
public class StudentLogger {
	public void log(String msg){
		System.out.println("log: "+msg);
	}
}
   
//InvocationHandler接口的实现类,java的动态代理中需要使用
public class MyHandler implements InvocationHandler {
	//目标对象
	private Object target; 
	private StudentLogger logger = new StudentLogger();

	public MyHandler() {
	}

	public MyHandler(Object target) {
		this.target = target;
	}

	// 参数1 将来所产生的代理对象 Proxy4
	// 参数2 将来需要调用到的目标对象里面真正的那个方法的镜像
	// 参数3 将来调用方法的时候所传的参数
	public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
		// 获得将来所调用方法的名字
		String methodName = m.getName();
		// 用日志记录输出一下
		logger.log(methodName + " is invoked...");
		// 用反射的方式去调用将来需要真正调用的方法.
		Object o = m.invoke(target, args);

		return o;
	}
	....
}

main:
	//目标对象
	IStudentService service = new StudentServiceImpl();
	
	//service是我们的目标对象
	//我们要给目标对象产生代理对象
	//目标对象service只能单独执行delete方法。
	//但是我们需要的是:先执行log日志方法再执行delete方法
	//目标对象service做不到这个要求,所以我们要给目标对象service生成一个代理对象去完成这俩个操作
	/*JDK动态代理的方式获得代理对象*/

	//获得目标对象的Class对象
	Class c = service.getClass();

	//获得目标对象的类加载器对象
	ClassLoader classLoader = c.getClassLoader();

	//获得目标对象所实现的所有接口
	Class[] interfaces = c.getInterfaces();

	//获得一个InvocationHandler接口的实现类对象,并把目标对象传进去
	InvocationHandler h = 
			new MyHandler(service);

	//参数1 目标对象的类加载器对象
	//参数2 目标对象所实现的所有接口. Class类型数组
	//参数3 InvocationHandler接口的实现类对象
	IStudentService proxy = 
		(IStudentService)Proxy.newProxyInstance
		(classLoader, interfaces, h);
	//这里的proxy是一个实现了IStudentService接口动态生成的代理类的对象
	proxy.delete();
```

###### 4.Cglib代理

1)JDK动态代理要求目标类实现接口,才能对其进行代理;
2)对于没有实现接口的类,可以使用CGLib进行动态代理;

3)CGLib采用了非常底层的字节码技术(依靠ASM的jar包,一种开源的java字节码编辑类库),其原理是通过字节码技术为目标类创建一个子类对象,并在子类对象中拦截所有父类方法的调用,然后在方法调用前调用后都可以加入自己想要执行的代码；（注:高版本的spring中,已经在spring-core-xx.jar中,把cglib和asm的核心代码整合了进来）

```Java
//目标类,没有实现任何接口
//通过字节码技术创建这个类的子类,实现动态代理
//这时,目标类作为父类,代理对象就是根据目标类动态生成的子类对象
	public class SayHello{
		public void say(){
			System.out.println("hello");
		}
	}

    public class CglibProxy implements MethodInterceptor{
		//创建出一个指定父类型的子类对象
		public Object getProxy(Class c){
			Enhancer enhancer = new Enhancer();
			//设置谁是父类
			enhancer.setSuperclass(c);
			enhancer.setCallback(this);
			//通过字节码技术动态创建子类实例
			return enhancer.create();
		}

		//intercept方法会拦截所有代理对象中方法的调用
		//obj   参数:将来生成的代理对象
		//method参数:将来代理对象所调用的方法的镜像
		//args  参数:将来代理对象调用方法时所传的参数
		//mproxy参数:该参数可以用来调用到父类中的方法
		public Object intercept(Object obj, Method method, Object[] args,MethodProxy mproxy) throws Throwable {
			System.out.println("目标方法执行之前");
			
			//调用父类中的方法
			Object result = mproxy.invokeSuper(obj, args);

			System.out.println("目标方法执行之后");
			return result;
		}
	}


	main:
		CglibProxy proxy = new CglibProxy();
		//通过生成子类的方式创建代理类
		SayHello s = (SayHello)
proxy.getProxy(SayHello.class);
		s.say();
```

###### 5.spring中aop的实现

明确aop中的概念：

```
1）spring中AOP功能的实现有以下俩种情况:
A:如果目标对象实现了接口,默认情况下会采用JDK的动态代理来实现AOP功能；
B:如果目标对象没有实现接口,spring会使用CGLIB的库来实现代理类实现AOP功能；
C：spring会在JDK动态代理和CGLIB之间自动选择；

2）AOP中的一些基本概念
AOP  		面向切面编程
aspect		切面/切面类
joinPoint	连接点
在spring的aop中只有类中的方法 可以做连接点,每一个方法都可以是一个连接点.
pointCut	切入点（一组连接点的集合）
advice		通知/拦截器 
用来控制切面类将来到底是织入到切入点的前面、后面或者是抛异常的时候.
adivsor（通知+切面(选择生效的方法)）	增强器
用来筛选类中的哪些方法是我们的连接点(哪些方法需要被拦截).
target		目标对象
proxy		代理对象
wave		织入

-----------------------

advice(通知)的类型:

        前置通知(Before advice): 
    		在某些连接点(join point)之前执行的通知;

        返回后通知(After returning advice): 
    		在某些连接点(join point)正常完成后执行的通知(方法正常结束,没有异常);

        抛出异常后通知(After throwing advice): 
    		在某些连接点(join point)抛出异常退出时执行的通知;

        后通知(After (finally) advice): 
    		当某些连接点(join point)退出的时候执行的通知;

        环绕通知(Around Advice): 
    		包围一个连接点(join point)的通知,例如事务的处理,就需要这样的通知,因为事务需要在方法前开启,在方法后提交,以及方法抛出异常时候回滚;

注:在spring中,连接点(join point)指的就是方法
```

代码示例：

```Java
//目标接口
	public interface OrderService {
        void saveOrder();
        void deleteOrder();
        String getById(String name);
	}
//目标对象
	public class OrderServiceImpl  implements OrderService{

	@Override
	public void saveOrder() {
		System.out.println("save.....");
	}
	@Override
	public void deleteOrder() {
		System.out.println("delete.....");
	}
	@Override
	public String getById(String name) {
		System.out.println("getById..........");
		return name;
	}
}
```

```Java xml
1)前置通知
public interface MethodBeforeAdvice extends BeforeAdvice {
	    void before(Method m, Object[] args, Object target) throws Throwable;
	}

前置通知测试类：
	public class beforeTest  implements MethodBeforeAdvice{
	@Override
	public void before(Method arg0, Object[] arg1, Object arg2) throws Throwable {
		System.out.println("before  "+arg0.getName()+"--"+arg1+"--"+arg2);
	}

	}
配置映射文件：
<bean name="target" 
   class="proxy.staticProxy.OrderServiceImpl">
</bean>
<bean name="before" class="aop.brfore.beforeTest">
</bean>
<bean name="proxy" 
class="org.springframework.aop.framework.ProxyFactoryBean">
<!-- 目标对象 -->
<property name="target"  ref="target"></property>
	<property name="interfaces">
         <array>          										<value>proxy.staticProxy.OrderService
         </value>
         </array>
    </property>
<!-- 通知方式 是数组-->
<property name="interceptorNames" value="before">
    <!-- 
    <array>
         <value>before</value>
    </array>
      -->
</property>
</bean>


2）后置通知
public interface AfterReturningAdvice extends AfterAdvice {
	    void afterReturning(Object returnValue, Method m, Object[] args, Object target) throws Throwable;

后置通知测试类：
public class afterTest implements AfterReturningAdvice{
	@Override
	public void afterReturning(Object arg0, Method arg1, Object[] arg2, Object arg3) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("after.... "+arg0+"--"+arg1.getName()+"--"+arg2[0]+"--"+arg3);
	}
}
配置xml文件
<bean name="target" class="proxy.staticProxy.OrderServiceImpl"></bean>
<bean name="after" class="aop.after.afterTest"></bean>
<bean name="proxy" class="org.springframework.aop.framework.ProxyFactoryBean">
      <property name="target" ref="target"</property>
       <property name="interfaces">
           	<array>				<value>proxy.staticProxy.OrderService</value>
           	</array>
       </property>
       <property name="interceptorNames"  value="after"></property>
</bean>


3)环绕通知
public interface MethodInterceptor extends Interceptor {
	      Object invoke(MethodInvocation invocation) throws Throwable;
}

环绕通知测试类：
	public class aroundTest implements  MethodInterceptor{
	@Override
	public Object invoke(MethodInvocation arg0) throws Throwable {
		System.out.println("开始执行方法"+arg0.getMethod().getName());
		Object  o=arg0.proceed();
		System.out.println("方法执行完成"+o);
		return o;
	}
配置xml文件:
<bean name="target" class="proxy.staticProxy.OrderServiceImpl"></bean>
<bean name="around" class="aop.around.aroundTest"></bean>
<bean name="proxy" class="org.springframework.aop.framework.ProxyFactoryBean">
    <property name="target" ref="target"></property>
    <property name="interfaces">
        <array>
		<value>proxy.staticProxy.OrderService</value>
        </array>
    </property>
    <property name="interceptorNames" value="around"></property>
</bean>


4) 异常通知
	//ThrowsAdvice 是一个空接口,起标识作用
	public interface ThrowsAdvice extends Advice {

	}
	
抛出异常后通知
	public class ThrowATest implements ThrowsAdvice{
		public void afterThrowing(Method method, Object[] args, Object target, Exception ex){
		System.out.println(ex.getMessage()+"     "+method.getName()+"--"+args+"---"+target);
	}

配置xml文件:
<bean name="target" class="proxy.staticProxy.OrderServiceImpl"></bean>
<bean name="throwA" class="aop.throwA.ThrowATest"></bean>
<bean name="before" class="aop.brfore.beforeTest"></bean>
<bean name="proxy" class="org.springframework.aop.framework.ProxyFactoryBean">
     <property name="target" ref="target"></property>
     <property name="interfaces">
          <array>          									<value>proxy.staticProxy.OrderService</value>
          </array>
     </property>
     <property name="interceptorNames">
          <array>
           	<value>throwA</value>
           	<value>before</value>
          </array>
     </property>
</bean>

5)advisor增强器
作用:筛选目标对象中要代理的方法,之前是把目标对象中的所有方法全部都进行代理
<!-- 配置目标对象 -->
<bean name="target" class="proxy.staticProxy.OrderServiceImpl"></bean>
<!-- 配置增强器 -->
<bean name="advisor" class="org.springframework.aop.support.RegexpMethodPointcutAdvisor">
    <property name="advice" ref="before"></property>
     <property name="patterns">
         <array>          										<value>.*Order</value>
         </array>
     </property>
</bean>
<!-- 配置代理对象 -->
<bean name="proxy" class="org.springframework.aop.framework.ProxyFactoryBean">
  <property name="target" ref="target"></property>
  <property name="interfaces">
     <array>         							    		<value>proxy.staticProxy.OrderService</value>
     </array>
  </property>
  <property name="interceptorNames" value="advisor"></property>
</bean>
           
同时产生多个代理对象:
		通过名字进行自动代理:BeanNameAutoProxyCreator类的使用;
        使用原因:虽然自动代理可以很方便的给xml文件中的目标对象设置对应的代理对象,但是并不是xml文件中的所有对象都是我们的目标对象,我们更想希望可以进一步筛选出某几个对象为我们的目标对象;
    	名字进行自动代理:解决了上面的问题,给我们提供了筛选目标对象的配置方式;
    		
<!--  配置目标对象（包含实现接口|没有实现接口） -->
<bean name="target" class="proxy.staticProxy.OrderServiceImpl"></bean>
<bean name="target1" class="proxy.staticProxy.OrderServiceImpl"></bean>
<bean name="target2" class="proxy.staticProxy.OrderServiceImpl"></bean>
<!-- 配置增强器 -->
<bean name="advisor" class="org.springframework.aop.support.RegexpMethodPointcutAdvisor">
    <property name="advice" ref="before"></property>
    <property name="patterns">
       <array>          									<value>.*Order</value>
       </array>
    </property>
</bean>
<!-- 配置xml文件 -->
<bean name="proxy" class="org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator">
    <property name="beanNames">
       <array>
          <value>us</value>
          <value>target</value>            					  <value>target1</value>         					  <value>target2</value>
       </array>
    </property>
    <property name="interceptorNames">
        <array>             									<value>advisor</value>
        </array>
    </property>
</bean>
注意：
使用byName自动代理的时候需要注意的方面:
    1.当前的配置里面"有没有"advisor的配置"都没关系".
    2.需要向自动代理类中注入被代理目标对象的名字已经advice或者advisor.
    3.不管目标对象是否实现了一个或多接口,自动代理的方式都能够为它产生代理对象.
    4.从spring容器中拿代理对象的时候,需要通过目标对象的名字来拿.
			
       
或者自动代理DefaultAdvisorAutoProxyCreator类的使用
	使用原因:在配置文件中我们往往需要给很多个目标对象设置代理对象,那么上面例子的方式就需要每个目标对象的代理对象都需要配置一套类似的标签.
	自动代理:可以用很少的配置为xml文件中的目标对象自动的生成对应的代理对象.
<!-- 目标对象 -->
<bean name="target" class="proxy.staticProxy.OrderServiceImpl"></bean>
<bean name="target1" class="proxy.staticProxy.OrderServiceImpl"></bean>
<bean name="target2" class="proxy.staticProxy.OrderServiceImpl"></bean>
<!-- 配置增强器 -->
<bean name="advisor" class="org.springframework.aop.support.RegexpMethodPointcutAdvisor">
     <property name="advice" ref="before"></property>
     <property name="patterns">
          <array>        										<value>.*Order</value>
          </array>
      </property>
</bean>
<!-- 配置自动注入标签 -->
<bean name="proxy" class="org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator">
</bean>

使用自动代理的时候需要注意的方面:
    		1.当前的配置里面"一定要有"一个advisor的配置,否则自动代理不成功.
    		2.不需要向自动代理类中注入任何信息.
    		3.不管目标对象是否实现了一个或多接口,自动代理的方式都能够为它产生代理对象(CGLib的方式).
    		4.从spring容器中拿代理对象的时候,需要通过目标对象的名字来拿.
			5.spring如何确定配置文件中哪个bean是作为目标对象:
通过advisor中筛选的方法,如果这个bean中含有advisor中所配置的方法,则这个bean将来为我们的目标对象进行代理;
```

​		关于spring aop相关的学习可参照 [spring aop](http://cmsblogs.com/?p=12825)

###### 6.<aop:config/>标签

```java
aop:config标签相关概念：
注意在使用之前需要在xml文件的beans标签中加入新的schame文件

使用aop的专用标签来完成相关的配置.其中主要表现是使用AspectJ的expression的操作:
execution(modifiers-pattern ret-type-pattern 
declaring-type-pattern name-pattern(param-pattern)
throws-pattern)除了返回类型模式,名字模式和参数模式以外,
所有的部分都是可选的。 返回类型模式决定了方法的返回类型必须依次
匹配一个连接点。 
你会使用的最频繁的返回类型模式是 *,它代表了匹配任意的返回类型。 一个全称限定的类型名将只会匹配返回给定类型的方法.

名字模式匹配的是方法名. 你可以使用 * 通配符作为所有或者部分
命名模式。 

参数模式稍微有点复杂:() 匹配了一个不接受任何参数的方法, 而 (..) 匹配了一个接受任意数量参数的方法(零或者更多). 

模式 (*) 匹配了一个接受一个任何类型的参数的方法。 模式 (*,String) 匹配了一个接受两个参数的方法,第一个可以是任意类型,第二个则必须是String类型

常见切入点表达式的例子。  
    1)任意包下的任意类中的公共方法的执行:  
		execution(public * *(..))
    2)任何一个以“set”开始的方法的执行:  
		execution(* set*(..))
    3)AccountService 接口的任意方法的执行:    
		execution(* com.briup.service.AccountService.*(..))
    4)定义在service包里的任意方法的执行:  
		execution(* com.briup.service.*.*(..))
    5)定义在service包或者子包里的任意方法的执行:  
		execution(* com.briup.service..*.*(..))
		execution(* com.briup.service..*.*(..)) or execution(* com.briup.dao..*.*(..))

注意:
	1.从spring容器中拿代理对象的时候也是要用目标对象的名字来拿.
	2.没有实现任何接口的目标对象也能产生代理对象.
```

```xml
<!-- 配置aop的代理 -->
<aop:config>
		<!-- 定义一个切入点 并给切入点起名为myPointCut -->
		<!-- 切入点是一组连接点的集合 -->
		<aop:pointcut expression="execution(public * com.briup.aop.service.*.*(..))" id="myPointCut"/>

		<!-- 定义哪一个advice在哪一个切入点上面起作用 -->
		<aop:advisor advice-ref="beforeAdvice" pointcut-ref="myPointCut" />
</aop:config>
	<!-- 
		expression="execution(public * com.briup.aop.service.*.*(..))"
		这个引号""里面就是用表达式的方式来定义切入点,只要是符合我们这个表达式要求的
		方法就是我们的连接点,连接点的集合就是我们要定义的切入点。
		表达式中从左到右的*号:
			第一个* 表示方法的返回类型不限。
			第二个* 表示包中的任意一个类
			第三个* 表示类中的任意一个方法
		同时方法的参数也没有限制.
	 -->
	 注意:<aop:config proxy-target-class="true"> 如果这样配置则是强制使用CGLIB方式进行代理
```

###### 7.在xml中配置aop

```Java xml
在一个切面类中定个多个方法,根据xml文件的配置每个方法都可以织入到切入点的不同位置,并且advice是在aop的标签中进行配置,不需要再写对应的advice类了
//这个类相当于我们之前的切面类
//只不过这个切面类中有很多方法都可以织入到切入点上面
//我们可以控制把这里的任何一个方法织入到任何一个切入点上面
	public class XmlHandler {
		
		public void beforeTest(JoinPoint p){
	System.out.println(p.getSignature().getName()+" before...");
		}
		
		public void afterTest(JoinPoint p){
	System.out.println(p.getSignature().getName()+" after...");
		}
		
		public void afterReturningTest(JoinPoint p){
	System.out.println(p.getSignature().getName()+" afterReturning");
		}
		
		//在和aroundAdvice结合的时候,这个方法一定要加上这个ProceedingJoinPoint类型的参数
		public Object aroundTest(ProceedingJoinPoint pjp)throws Throwable{
			//JoinPoint对象不能调用连接点所表示的方法 
			//ProceedingJoinPoint能调用连接点所表示的方法 pjp.proceed()
			System.out.println(pjp.getSignature().getName()+" is start..");
			//调用到连接点方法
			Object obj = pjp.proceed();
			System.out.println(pjp.getSignature().getName()+" is end..");
			return obj;
		}
		
		public void throwingTest(JoinPoint p,Exception ex){
			System.out.println(p.getSignature().getName()+" is throwing..."+ex.getMessage());
			
		}
	}
	
xml文件配置:
<!-- 配置dao层对象 -->
<bean id="dao" 
class="aop.dao.AccountDaoImpl"/>
<!-- 配置目标对象 -->
<bean name="target" 
class="aop.service.AccountServiceImpl">
<property name="accountDao" ref="dao"></property>
</bean>
<!-- 配置切面类 -->
<bean name="handler" class="aop.xml.XmlHandler"></bean>
<!-- 配置aop的代理 -->
<aop:config>
<!-- 定义切入点名为myPointCut -->
<aop:pointcut expression="execution(public * aop.service.*.*(..))" id="myPointCut"/>
<!-- 定义切面类 以及需要使用的advice -->
<aop:aspect id="aspect" ref="handler">
<!-- 表示beforeAdvice会把切面类handler中的beforeTest方法织入到名字叫myPointCut的切入点上面 -->
<aop:before method="beforeTest" pointcut-ref="myPointCut"/>
<!-- after表示不管方法是否正常结束都会起作用 -->
<aop:after method="afterTest" pointcut-ref="myPointCut"/>
<!-- after-returning表示方法正常结束才会起作用(抛异常时候不起作用) -->
 <aop:after-returning method="afterReturningTest" pointcut-ref="myPointCut"/>
			<aop:around method="aroundTest" pointcut-ref="myPointCut"/>
			<!-- throwing="ex"表示throwingTest方法中接收异常对象的名字一定要是ex -->
            <aop:after-throwing method="throwingTest" pointcut-ref="myPointCut" throwing="ex"/>
		</aop:aspect>
</aop:config>

注意:<aop:config proxy-target-class="true"> 如果这样配置则是强制使用CGLIB方式进行代理


```

###### 8.使用注解配置aop

其实就是在上面的类XmlHandler中加入上注解,然后去掉xml中的aop标签配置,这里把类改名为AnnotationHandler

```Java xml

	@Component
	@Aspect
	public class AnnotationHandler {
		/*
		 * 在一个方法上面加上注解来定义切入点
		 * 这个切入点的名字就是这个方法的名字
		 * 这个方法本身不需要有什么作用
		 * 这个方法的意义就是:给这个 @Pointcut注解一个可以书写的地方
		 * 因为注解只能写在方法、属性、类的上面,并且方法名作为切入点的名字
		 * */
		@Pointcut("execution(public * com.briup.aop.service..*.*(..))")
		public void myPointCut(){}
		
		//注:这里面的所有方法的JoinPoint类型参数都可以去掉不写,如果确实用不上的话
		@Before("myPointCut()")
		public void beforeTest(JoinPoint p){
			System.out.println(p.getSignature().getName()+" before...");
		}
		
		
		/*
		 * @After和@AfterReturning
		 * 
		 * @After标注的方法会在切入点上的方法结束后被调用(不管是不是正常的结束).
		 * @AfterReturning标注的方法只会在切入点上的方法正常结束后才被调用.
		 * */
		@After("myPointCut()")
		public void afterTest(JoinPoint p){
			System.out.println(p.getSignature().getName()+" after...");
		}
		@AfterReturning("myPointCut()")
		public void afterReturningTest(JoinPoint p){
			
			System.out.println(p.getSignature().getName()+" afterReturning");
			
		}
		
		@Around("myPointCut()")
		public Object aroundTest(ProceedingJoinPoint pjp)throws Throwable{
			System.out.println(pjp.getSignature().getName()+" is start..");
			//调用连接点的方法去执行
			Object obj = pjp.proceed();
			System.out.println(pjp.getSignature().getName()+" is end..");
			return obj;
		}
		
		//在切入点中的方法执行期间抛出异常的时候,会调用这个 @AfterThrowing注解所标注的方法
							@AfterThrowing(value="myPointCut()",throwing="ex")
		public void throwingTest(JoinPoint p,Exception ex){
			System.out.println(p.getSignature().getName()+" is throwing..."+ex.getMessage());
			
		}
		
	}
	
xml配置:注意给例子中使用的其他的类上面也使用注解
<aop:aspectj-autoproxy/>
<context:component-scan base-package="aop"/>
注意:这样配置则是强制使用CGLIB进行代理
<aop:aspectj-autoproxy proxy-target-class="true"/>
```

