package aop.spring.defineaopConfig;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 自定义通知类
 * @author WE
 *
 */
public class DefineNoticeAdvice {
	/**
	 * 前置通知
	 * 参数必须是JoinPoint类型
	 */
	public void beforeTest(JoinPoint point){
		System.out.println("before....");
	}
	/**
	 * 后置通知
	 */
	public void afterTest(JoinPoint point){
		//获取目标对象
		System.out.println(point.getTarget());
		System.out.println("afterTest.......");
	}
	/**
	 * 后置通知2
	 */
	public void afterReturn(JoinPoint point){
		System.out.println("afterReturn....");
	}
	/**
	 * 环绕通知
	 */
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
	 */
	public void throwE(JoinPoint point,Exception ex){
		System.out.println("Exception....."+ex.getMessage());
		
	}
	

}
