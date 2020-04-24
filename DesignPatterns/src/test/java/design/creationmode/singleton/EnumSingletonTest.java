package design.creationmode.singleton;

import design.creationmode.singleton.register.EnumSingleton;

import java.lang.reflect.Constructor;

/**
 * @Description:枚举式单例测试
 * @Author:BigRedCaps
 */
public class EnumSingletonTest
{
    public static void main (String[] args)
    {
//        EnumSingleton instance = EnumSingleton.getInstance();
//        instance.setData(new Object());

        //Enum
        //这里查看枚举的源码可知，枚举没有无参的构造器
        //因此，利用反射，获取带两个参数枚举构造器；

        //之后，又会出现无权限访问private的构造器
        //因此强吻一下

        //强吻依然不行，JDK官方规定枚举类型的private修饰的构造器不能通过反射创建
        //if ((clazz.getModifiers() & Modifier.ENUM) != 0)
        //            throw new IllegalArgumentException("Cannot reflectively create enum objects");


        //因此，枚举式单例作为最优雅的单例写法

        //但是它有点像饿汉式的用法，在一开始就创建了一个单例对象，放在Map集合中（可查看Enum的源码），因此会有内存浪费；

        //Spring官方又把这种枚举式单例做了改良；
        try
        {
            Class clazz = EnumSingleton.class;
            Constructor constructor = clazz.getDeclaredConstructor(String.class,int.class);
            constructor.setAccessible(true);
            Object o = constructor.newInstance();
            System.out.println(o);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
