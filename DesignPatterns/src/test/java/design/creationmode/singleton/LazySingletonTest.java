package design.creationmode.singleton;

/**
 * @Description:懒汉式单例模式线程安全测试
 * @Author:BigRedCaps
 */
public class LazySingletonTest
{
    public static void main (String[] args)
    {
        Thread t1 = new Thread(new ExectorThread());
        Thread t2 = new Thread(new ExectorThread());
        t1.start();
        t2.start();
        System.out.println("End");
    }



}
