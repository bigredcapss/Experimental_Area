package aop.demo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author BigRedCaps
 * @date 2021/3/20 11:33
 */
@Component
@Aspect
public class ServiceAspect
{
    @Pointcut(value = "execution(* aop.demo.*.*(..))")
    public void pointCut(){}

    @Before(value = "pointCut()")
    public void methodBefore(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("执行目标方法【"+methodName+"的前置通知，入参："+ Arrays.toString(joinPoint.getArgs()));
    }

    @After(value = "pointCut()")
    public void methodAfter(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("执行目标方法【"+methodName+"的后置通知，入参："+Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(value = "pointCut()")
    public void methodReturn(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("执行目标方法【"+methodName+"的返回通知，入参："+Arrays.toString(joinPoint.getArgs()));
    }

    @AfterThrowing(value = "pointCut()")
    public void methodThrowing(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("执行目标方法【"+methodName+"的异常通知，入参："+Arrays.toString(joinPoint.getArgs()));
    }

}
