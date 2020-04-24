package design.creationmode.singleton;

import design.creationmode.singleton.threadlocal.ThreadLocalSingleton;

/**
 * @Description:创建多个线程抢夺getInstance()资源
 * @Author:BigRedCaps
 */
public class ExectorThread implements Runnable
{
    @Override
    public void run ()
    {
        //LazySimpleSingleton instance = LazySimpleSingleton.getInstance();
        //LazyDoubleCheckSingleton instance = LazyDoubleCheckSingleton.getInstance();

        ThreadLocalSingleton instance = ThreadLocalSingleton.getInstance();
        System.out.println(ThreadLocalSingleton.getInstance());
        System.out.println(Thread.currentThread().getName()+"--"+instance);
    }
}
