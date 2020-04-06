package aop.spring.before;

import java.lang.reflect.Method;


import org.springframework.aop.MethodBeforeAdvice;


/**
 * 前置通知：在目标对象的方法执行之前加入的操作
 * @author WE
 *
 */
/**
 * xml中是固定配法，不必思考逻辑
 * @author WE
 *
 */
public class BeforeAdviceTest implements MethodBeforeAdvice{

	/**
	 * 第一个参数：目标对象的方法的镜像
	 * 第二个参数：目标对象的方法的参数
	 * 第三个参数：目标对象
	 */
	@Override
	public void before(Method arg0, Object[] arg1, Object arg2) throws Throwable {
		System.out.println("before......");
	}
	

}
