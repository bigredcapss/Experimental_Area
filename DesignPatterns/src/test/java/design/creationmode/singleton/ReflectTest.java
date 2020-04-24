package design.creationmode.singleton;

import design.creationmode.singleton.lazy.LazyInnerClassSingleton;

import java.lang.reflect.Constructor;

/**
 * @Description:反射破坏单例
 * @Author:BigRedCaps
 *
 * 由于前面单例对象的创建都是通过构造器来实现的，尽管构造器是private类型的，那我可以利用反射拿到所有类型的构造器，然后
 * 强吻创建对象；
 *
 *
 */
public class ReflectTest
{
    public static void main (String[] args)
    {
        try
        {
            Class<?> clazz = LazyInnerClassSingleton.class;
            Constructor c = clazz.getDeclaredConstructor(null);

            //强吻暴力访问,不管你是private类型还是public类型
            c.setAccessible(true);

            //利用反射创建对象
            Object instance1 = c.newInstance();
            Object instance2 = c.newInstance();

            System.out.println(instance1);
            System.out.println(instance2);
            System.out.println(instance1==instance2);


        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
