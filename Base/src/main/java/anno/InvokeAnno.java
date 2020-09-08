package anno;

import java.lang.annotation.*;

/**
 * @Description:自定义注解--该注解表明要执行哪个类的哪个方法
 * @Author:BigRedCaps
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InvokeAnno
{
    String className();
    String methodName();
}
