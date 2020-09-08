package anno;

import java.lang.reflect.Method;

/**
 * @Description:
 * @Author:BigRedCaps
 */
@InvokeAnno(className = "anno.Student",methodName = "show")
public class InvokeAnnoTest
{
    public static void main (String[] args) throws Exception
    {
        // 获取类对象
        Class<InvokeAnnoTest> clazz = InvokeAnnoTest.class;
        // 获取类对象上的注解
        InvokeAnno annotation = clazz.getAnnotation(InvokeAnno.class);
        /**
         *  注解本质是接口-->获取到的其实是接口的实现
         *  public class MyInvokAnno implements InvokAnno{
         *
         *      String className(){
         *          return "com.gupao.edu.anno2.Student1";
         *      }
         *      String methodName(){
         *          return "show";
         *      }
         *  }
         */
        // 获取注解中对应的属性
        String className = annotation.className();
        String methodName = annotation.methodName();
        System.out.println(className);
        System.out.println(methodName);

        Class<?> annoClazz = Class.forName(className);
        Object obj = annoClazz.newInstance();
        Method show = annoClazz.getDeclaredMethod("show");
        show.invoke(obj);

    }
}
