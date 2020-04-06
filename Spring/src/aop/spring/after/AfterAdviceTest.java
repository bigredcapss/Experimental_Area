package aop.spring.after;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;
/**
 * 后置通知：在目标对象的方法执行之后加入的操作
 * @author WE
 *
 */
public class AfterAdviceTest implements AfterReturningAdvice{

	/**
	 * 第一个参数：目标对象执行之后的返回值
	 * 第二个参数：目标对象执行方法的镜像
	 * 第三个参数：目标对象执行方法的参数
	 * 第四个参数：目标对象
	 */
	@Override
	public void afterReturning(Object arg0, Method arg1, Object[] arg2, Object arg3) throws Throwable {
		// TODO Auto-generated method stub
//		System.out.println(arg0);
//		System.out.println(arg1);
		System.out.println("after.....");
		
	}

}
