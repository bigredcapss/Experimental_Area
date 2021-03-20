package aop.demo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Aop测试
 * @author BigRedCaps
 * @date 2021/3/20 11:29
 */
@EnableAspectJAutoProxy
@Configuration
@ComponentScan(basePackages = {"aop.demo"})
public class Test
{
    public static void main (String[] args)
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Test.class);
        IService service = context.getBean("service",IService.class);
        service.doService();
    }
}
