package design.creationmode.singleton.lazy;

/**
 * @Description:懒加载机制--双重检查锁方法--保证线程安全并提高性能
 * @Author:BigRedCaps
 * 缺点：代码可读性不高，不够优雅；而且加锁多少对性能会有一点影响；
 *
 * 
 *
 */
public class LazyDoubleCheckSingleton
{
    private volatile static LazyDoubleCheckSingleton lazyDoubleCheckSingleton = null;

    private LazyDoubleCheckSingleton (){}

    //这里仍然会出现后者覆盖前者的情况
//    public static LazyDoubleCheckSingleton getInstance(){
//            if(lazyDoubleCheckSingleton == null)
//            {
//                synchronized(LazyDoubleCheckSingleton.class){
//                      lazyDoubleCheckSingleton = new LazyDoubleCheckSingleton();
//                }
//            }
//        return lazyDoubleCheckSingleton;
//    }


    //解决办法3
    public static LazyDoubleCheckSingleton getInstance(){
        //第一重检查检查是否要阻塞
        if(lazyDoubleCheckSingleton == null)
        {
            synchronized (LazyDoubleCheckSingleton.class)
            {
                //第二重检查检查是否重新创建实例
                if (lazyDoubleCheckSingleton == null)
                {
                    lazyDoubleCheckSingleton = new LazyDoubleCheckSingleton();
                    //实例对象的内存与引用变量的创建顺序不同，可能会出现线程紊乱，引起指令重排序问题；因此
                    // lazyDoubleCheckSingleton变量要加volatile关键字，避免指令重排序问题
                }
            }
        }
        return lazyDoubleCheckSingleton;
    }
}
