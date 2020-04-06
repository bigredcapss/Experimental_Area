package aop.spring.around;


import java.util.Arrays;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
/**
 * 环绕通知：在目标对象的方法执行前后加操作
 * @author WE
 *
 */
public class AroundAdviceTest implements MethodInterceptor{

	@Override
	public Object invoke(MethodInvocation arg0) throws Throwable {
		//获取调用目标对象方法的镜像
		System.out.println(arg0.getMethod());
		//获取调用目标对象方法的参数
		System.out.println(Arrays.toString(arg0.getArguments()));
		System.out.println("before.....");
		//执行目标对象的方法
		Object obj = arg0.proceed();
		System.out.println("after.....");
		return obj;
	}




}
