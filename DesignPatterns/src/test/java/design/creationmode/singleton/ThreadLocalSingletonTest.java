package design.creationmode.singleton;

import design.creationmode.singleton.threadlocal.ThreadLocalSingleton;

/**
 * ThreadLocal单例模式测试
 *
 * 这里尽管出现的多个不同实例，但仍然是线程安全的，因为ThreadLocal保证当前实例在当前线程内唯一；
 * 可以查看ThreadLocal的源码
 *
 *
 */
public class ThreadLocalSingletonTest {

    public static void main(String[] args) {
        System.out.println(ThreadLocalSingleton.getInstance());
        System.out.println(ThreadLocalSingleton.getInstance());
        System.out.println(ThreadLocalSingleton.getInstance());

        Thread t1 = new Thread(new ExectorThread());
        Thread t2 = new Thread(new ExectorThread());
        t1.start();
        t2.start();
        System.out.println("End");
    }
}
