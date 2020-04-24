package design.creationmode.singleton.lazy;

/**
 * @Description:懒汉式单例模式
 * @Author:BigRedCaps
 * 优点：避免了内存的浪费；
 *
 * 缺点：线程不安全
 *
 * 不加锁时，会随机出现两种运行结果：
 *      同一个实例:
 *          --> 1.正常顺序执行；
 *          --> 2.后者覆盖前者；
 *      不同的实例:
 *          --> 1.同时进入条件，按顺序返回；
 *
 * 解决办法1：加锁，使用同步方法；但线程过多，会造成线程阻塞，引起性能下降问题；
 * public synchronized static LazySimpleSingleton getInstance(){}
 *
 * 解决办法2：仅仅是换了一个地方阻塞而已，还是会阻塞，对性能提高并没有什么作用；
 *
 * 解决办法3：双重检查锁方法--参照LazyDoubleCheckSingleton.java
 *
 *
 */
public class LazySimpleSingleton
{
    private static LazySimpleSingleton lazySimpleSingleton = null;

    private LazySimpleSingleton (){}

    //解决办法1
//    public synchronized static LazySimpleSingleton getInstance(){
//        if(lazySimpleSingleton == null)
//        {
//            lazySimpleSingleton = new LazySimpleSingleton();
//        }
//        return lazySimpleSingleton;
//    }
    //解决办法2：
    public static LazySimpleSingleton getInstance(){
        synchronized(LazySimpleSingleton.class){
            if(lazySimpleSingleton == null)
            {
                lazySimpleSingleton = new LazySimpleSingleton();
            }
        }
        return lazySimpleSingleton;
    }

}
