package aop.spring.annoAopConfig;

import org.aspectj.lang.JoinPoint;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 自定义通知类
 * @Aspect:表示当前类中的方法全是作用于切面上的通知；
 * 1.用注解的方式构建切面
 * 2，通知上面加注解
 * @author WE
 *
 */
@Component
@Aspect
public class DefineNoticeAdviceAnno {
	/**
	 * 构建切面的方法，切面的名字就是methodName(),方法一般不使用
	 */
	@Pointcut(value="execution(* pojo.*.save*(..))")
	public void mypointcut(){}
	
	
	
	/**
	 * 前置通知
	 * 参数必须是JoinPoint类型
	 * @Before("mypointcut()")注解该方法前置通知，参数是切面的名字
	 */
	@Before("mypointcut()")
	public void beforeTest(JoinPoint point){
		System.out.println("before....");
	}
	/**
	 * 后置通知
	 */
	@After("mypointcut()")
	public void afterTest(JoinPoint point){
		//获取目标对象
		System.out.println(point.getTarget());
		System.out.println("afterTest.......");
	}
	/**
	 * 后置通知2
	 */
	@AfterReturning("mypointcut()")
	public void afterReturn(JoinPoint point){
		System.out.println("afterReturn....");
	}
	/**
	 * 环绕通知
	 */
	@Around(value="mypointcut()")
	public Object aroundTest(ProceedingJoinPoint point){
		Object obj =null;
		try {
			System.out.println("before.....");
			//执行目标对象中的方法
			point.proceed();
			System.out.println("after......");
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
	/**
	 * 异常通知
	 * 第一个参数JoinPoint
	 * 第二个参数异常类
	 * @AfterThrowing注解中throwing对应的名字和方法中参数的名字保持一致
	 */
	@AfterThrowing(value="mypointcut()",throwing="ex")
	public void throwE(JoinPoint point,Exception ex){
		System.out.println("Exception....."+ex.getMessage());
		
	}
	

}
