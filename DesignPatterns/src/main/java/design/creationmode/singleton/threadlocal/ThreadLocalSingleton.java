package design.creationmode.singleton.threadlocal;

/**
 * ThreadLocal单例
 * 保证在线程内部的全局唯一，且天生线程安全，虽不常用，但具有一定的参考意义；
 * 在做数据源切换时，会用到ThreadLocal
 *
 * 阅读ThreadLocal源码：
 * ThreadLocal中有一个get()方法-->ThreadLocalMap-->从Map中获得一个值，这个值就代表当前对象所在的线程
 */
public class ThreadLocalSingleton {
    private static final ThreadLocal<ThreadLocalSingleton> threadLocaLInstance =
            new ThreadLocal<ThreadLocalSingleton>(){
                @Override
                protected ThreadLocalSingleton initialValue() {
                    return new ThreadLocalSingleton();
                }
            };

    private ThreadLocalSingleton(){}

    public static ThreadLocalSingleton getInstance(){
        return threadLocaLInstance.get();
    }
}
