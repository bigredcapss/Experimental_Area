package aop.spring.throwex;

import java.lang.reflect.Method;


import org.springframework.aop.ThrowsAdvice;
/**
 * 当执行目标对象方法报错的时候执行的代码
 * @author WE
 *
 */
public class ThrowAdviceTest implements ThrowsAdvice{
	public void afterThrowing(Method method, Object[] args, Object target, Exception ex){
		System.out.println("exception......");
	}

}
