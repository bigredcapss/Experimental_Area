package reflect;

import java.lang.annotation.Annotation;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;

/**
 * @Description:反射API应用
 * @Author:BigRedCaps
 */
//定义可重复注解
@Repeatable(Annos.class)
@interface Anno{}
@Retention(value = RetentionPolicy.RUNTIME)
@interface Annos{
    Anno[] value();
}

//使用四个注解修改该类
@SuppressWarnings(value = "unchecked")
@Deprecated
@Anno
@Anno
public class ClassTest {
    //定义一个私有构造器
    private ClassTest(){

    }
    //定义一个有参构造器
    public ClassTest(String name){
        System.out.println("执行有参数的构造器");
    }
    //定义一个无参数的info方法
    public void info(){
        System.out.println("执行无参数的info方法");
    }
    //定义一个有参数的info方法
    public void info(String str){
        System.out.println("执行有参数的info方法"+",其str参数值："+str);
    }
    //内部类
    class Inner{

    }

    public void replace(String str, List<String> list){}

    public static void main(String[] args) throws Exception {
        //Class对象clazz对应类ClassTest的信息

        //通过反射查看类信息
        Class<ClassTest> clazz = ClassTest.class;
        //返回Class对象所对应类的全部构造器
        Constructor[] ctors = clazz.getDeclaredConstructors();
        System.out.println("ClassTest类的全部构造器：");
        for (Constructor constructor : ctors){
            System.out.println(constructor);
        }
        //获取Class对象对应类的public构造器
        Constructor[] constructors = clazz.getConstructors();
        System.out.println("ClassTest类的public构造器");
        for (Constructor c:constructors) {
            System.out.println(c);
        }
        //获取Class对象所对应类的指定方法
        System.out.println("ClassTest类的info方法："+clazz.getMethod("info",String.class));
        //获取Class对象所对应类的全部注解
        Annotation[] annotations = clazz.getAnnotations();
        System.out.println("ClassTest类的全部注解如下：");
        for(Annotation annotation:annotations){
            System.out.println(annotation);
        }
        //获取Class对象的@Suppresswarning注解
        System.out.println("ClassTest类的SuppressWarning注解："+clazz.getAnnotationsByType(SuppressWarnings.class));

        //获取指定的构造器
        Constructor c = clazz.getConstructor(String.class);
        System.out.println(c);
        //获取Class对象的全部内部类
        Class<?>[] innerClass = clazz.getDeclaredClasses();
        System.out.println("ClassTest的全部内部类如下：");
        for(Class cl:innerClass){
            System.out.println(cl);
        }
        //使用Class.forName加载ClassTest的Inner内部类
//        Class incla = Class.forName("ClassTest$Inner");
//        System.out.println("incla对应类的外部类："+incla.getDeclaringClass());
        System.out.println("ClassTest的包名："+clazz.getPackage());
        System.out.println("ClassTest的父类名："+clazz.getSuperclass());


        //获取replace方法
        System.out.println("获取ClassTest类的replace方法：");
        Method replace = clazz.getMethod("replace", String.class, List.class);
        System.out.println("获取replace方法的参数个数："+replace.getParameterCount());
        System.out.println("获取replace方法的所有参数信息：");
        Parameter[] parameters = replace.getParameters();
        for (Parameter parameter:parameters)
        {
            System.out.println("参数类型："+parameter.getType());
            System.out.println("参数名字："+parameter.getName());
            System.out.println("泛型类型："+parameter.getParameterizedType());
        }




    }



}
