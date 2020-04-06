package bean;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * @Component:让spring构建当前对象，相当于在xml文件中配置了bean标签
 * @author WE
 *@Component("dest")给当前对象起名字,默认类名首字母小写
 *
 *@Scope(""):指定构建的对象是单例还是非单例
 *singleton、prototype、session、global session、request后三个用在web项目中
 *request:每次请求构建一个对象
 *session:第一次请求构建一个会话对象，在浏览器开启时，再次请求用的是同一个session对象
 *global session:全局可共享的session对象
 *
 *@Component细分，区分web层次用的
 *@Controller ：作用于控制层web
  @Service ：作用于业务逻辑层service
  @Repository：作用于数据持久层dao
 */
@Component("dest")
public class DestBean {
	private SrcBean src;

	public SrcBean getSrc() {
		return src;
	}

	/**
	 * @PostConstruct:初始化对象时执行的方法注解
	 * @PreDestroy：从spring中移除对象时调用的方法注解;该方法的执行保证当前对象是单例
	 */
	@PostConstruct
	public void init_dest(){
		System.out.println("inti....init....");
	}
	
	@PreDestroy
	public void destroy_dest(){
		System.out.println("destroy.....");
	}
	
	/**
	 * 自动装配
	 * @param src
	 * 1.@AutoWired:先基于类型找，找到多个，在基于名字找(基于名字查找是基于变量的名字查找)
	 * 
	 * 出现的位置：
	 * 1.set方法2.全局变量3.构造器
	 * 
	 * @AutoWired(required=true)表示基于名字或类型一定要找到，找不到就报错，；required=false表示不一定非要找到，
	 * 找到了就装配，找不到忽略
	 * @Qualifier("")不能用在构造器中，可以用在参数声明中；声明名字，基于名字找到；如果构造器，全局变量，set方法
	 * 都设置了该注解则set方法生效；如果构造器和全局变量都设置了该注解，则全局变量生效
	 * 
	 * 2.@Resource:也是完成自动注入对象的注解，默认按名字查找，名字找不到按类型找
	 * 位置：set方法上，set方法去掉set首字母小写的
	 * @Resource(name="src")只会基于名字src找，找不到报错
	 * @Resource(type="SrcBean.Class")基于类型找，但在有名字的情况下，还是基于名字找
	 * 既name和type属性并存的情况下，按name查找，名字找不到直接报错
	 */
	//@Autowired
	//@Resource(name="src")
	@Resource
	public void setSrc(SrcBean src) {
		this.src = src;
	}

	@Override
	public String toString() {
		return "DestBean [src=" + src + "]";
	}
	

}
