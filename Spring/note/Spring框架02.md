###### 1.spring 自定义属性编辑器PropertyEditor

应用场景：当我们需要一个类的多个不同对象,如果在容器中实例化多个bean,显然比较麻烦,spring中我们可以使用属性编辑器来将特定的字符串转换为对象 :String-->object；

java.beans.PropertyEditor(JDK中)用于将xml文件中字符串转换为特定的类型，JDK为我们提供一个实现类PropertyEditorSupport；

spring在注入时,如果遇到类型不一致则会去调用相应的属性编辑器进行转换,调用属性编辑器的setAsText(String str)进行处理,调用其getValue()获取处理后得到的对象;

```Java
e.g
自定义编辑器AddressEditor.java：
public class AddressEditor extends PropertyEditor{
    public void setAsText(String text) throws IllegalArgumentException{
            String str = text.split("[,]");
            Address addr = new Address(str[0],str[1],Integer.parseInt(str[2]));
            //设置到父类中,以便Spring调用getValue可以获得
            setValue(addr);
     }
}

User.java:
public class User {
    private Address address;
    //省略getter setter
}

Address.java
public class Address {
    private String city;
    private String street;
    private int code;
    //省略getter setter
}

applicationContext.xml文件:
customEditors 是CustomEditorConfigurer类的一个属性，这个属性的值是一个Map类型的，Map的KEY 是需要编辑的属性的类型，对应的value值是属性编辑器类；

<bean id="customEditorConfigurer" class="org.springframework.beans.factory.config.CustomEditorConfigurer">
     <property name="customEditors">
        <map>
            <entry key="ioc.Address"> //key为目标类
				<bean class="ioc.AddressEditor" /> //属性编辑器
            </entry>
        </map>
     </property>
</bean>

<bean id="user" class="ioc.User">
  <property name="address" value="城市,街道,471900" />  //此处串会被转换
</bean>
      
```

​		关于spring自定义属性编辑器可参考 [spring自定义属性编辑器](https://my.oschina.net/zion/blog/817540)

###### 2.spring ioc中的注解

```xml
1）@Autowired
spring 通过BeanPostProcessor接口的实现类 对 @Autowired 进行解析，所以要让 @Autowired 起作用必须事先在 Spring 容器中声明 AutowiredAnnotationBeanPostProcessor；
@Autowired默认按照类型匹配的方式进行注入；
@Autowired注解可以用于成员变量、setter方法、构造器函数等
使用@Autowired注解先按照byType查找，如果找到多个，则按照byName的形式查找，否则报错;
spring 允许我们通过 @Qualifier 注释指定注入 Bean 的名称。@Autowired 和 @Qualifier 结合使用时，自动注入的策略变成 byName ;

<!-- 该 BeanPostProcessor 将自动起作用，对标注 @Autowired 的 Bean 进行自动注入 -->  
<bean class="org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor"/>

或者使用下面的隐式注册（隐式注册 post-processors 包括了
 AutowiredAnnotationBeanPostProcessor，   CommonAnnotationBeanPostProcessor，PersistenceAnnotationBeanPostProcessor， RequiredAnnotationBeanPostProcessor）

2）@Resource
@Resource 的作用相当于 @Autowired，只不过 @Autowired 按 byType 自动注入，@Resource 默认按先用byName,如果找不到合适的就再用byType来注入；
要让JSR-250(@Resource属于这种注解 ) 的注解生效，除了在 Bean 类中标注这些注解外，还需要在 spring 容器中注册一个负责处理这些注解的 BeanPostProcessor
<bean class="org.springframework.context.annotation.CommonAnnotationBeanPostProcessor"/> 
@Resource 有两个属性是比较重要的，分别是 name 和 type，Spring 将 @Resource 注解的 name 属性解析为 Bean 的名字，而 type 属性则解析为 Bean 的类型。所以如果使用 name 属性，则使用 byName 的自动注入策略，而使用 type 属性时则使用 byType 自动注入策略。如果既不指定 name 也不指定 type 属性，这时默认将通过反射机制使用 byName 自动注入策略;

3）@PostConstruct 和 @PreDestroy
标注了 @PostConstruct 注解的方法将在类实例化后调用，而标注了 @PreDestroy 的方法将在实例销毁之前调用；

4）@Component
使用@Component注解可以直接定义Bean，而无需在xml定义。但是若两种定义同时存在，xml中的定义会覆盖类中注解的Bean定义;

@Component有一个可选的入参，用于指定 Bean 的名称;

默认情况下通过 @Component 定义的 Bean 都是 singleton 的，如果需要使用其它作用范围的 Bean，可以通过 @Scope 注释来达到目标，其默认作用域是"singleton",如果要换成其他作用区域，直接后面添加类型即可.例如 @Scope("prototype") ，spring2.0后 又增加了request ，session和global session 4个作用区;

5）<context:component-scan/> 允许自定义类型过滤器将包下的某些类纳入或排除,spring 支持以下 4 种类型的过滤方式：
type(过滤器类型)	expression(表达式范例)
annotation	   org.example.SomeAnnotation
assignable	   org.example.SomeClass
regex		   org\.example\.Default.*
aspectj		   org.example..*Service+

下面这个XML配置会忽略所有的@Repository注解
<beans ...>  
         <context:component-scan base-package="org.example">  
         <context:include-filter type="regex" expression=".*Stub.*Repository"/>  
         <context:exclude-filter type="annotation" expression="org.springframework.stereotype.Repository"/>  
         </context:component-scan>  
</beans>  

6）Spring 2.5以后引入了更多典型化注解(stereotype annotations)： @Component、@Service和 @Controller；

@Component是所有受Spring管理的组件的通用形式； 

@Repository、@Service和 @Controller则是@Component的细化， 用来表示更具体的用例(例如，分别对应了持久化层、业务层和控制层)

7）要检测这些类中的注解并注册相应的bean，需要在XML文件中包含以下元素，其中'basePackage'是两个类的公共父包 (或者可以用逗号分隔的列表来分别指定包含各个类的包)。

<?xml version="1.0" encoding="UTF-8"?>       
<beans xmlns="http://www.springframework.org/schema/beans"  
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
           xmlns:context="http://www.springframework.org/schema/context"  
           xsi:schemaLocation="http://www.springframework.org/schema/beans   
               http://www.springframework.org/schema/beans/spring-beans-3.2.xsd  
               http://www.springframework.org/schema/context  
               http://www.springframework.org/schema/context/spring-context-3.2.xsd">  
                    
<context:component-scan base-package="自己的package"/> 
</beans>  

此外，在使用组件扫描元素时，AutowiredAnnotationBeanPostProcessor 和CommonAnnotationBeanPostProcessor会隐式地被包括进来。 也就是说，连个组件都会被自动检测并织入 - 所有这一切都不需要在XML中提供任何bean配置元数据。
```

​		关于spring自定义类型过滤器可参考 [spring类型过滤器](https://blog.csdn.net/u012326462/article/details/82765485)

​		关于spring的自动扫描和管理bean可参考[spring自动扫描和管理bean](https://blog.csdn.net/qq_36098284/article/details/80663860)

