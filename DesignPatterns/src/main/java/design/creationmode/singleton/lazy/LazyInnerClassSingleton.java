package design.creationmode.singleton.lazy;

/**
 * @Description:懒加载单例模式--静态内部类解决办法
 * @Author:BigRedCaps
 *
 * 优点：写法优雅，利用Java语法本身的特点，性能高，避免了内存浪费；
 * 缺点：反射破环单例；其实前面的写法都会被反射破坏；
 *
 * 类加载时，加载ClassPath路径下的:LazyInnerClassSingleton.class
 *          内部类在ClassPath下是这样的：LazyInnerClassSingleton.class$LazyHolder.class
 *          当我们在程序中用到这个内部类的时候才加载该内部类；
 *          
 * 反射破坏单例解决办法1：在构造器中作处理，当多次创建对象时，进行判断，抛出异常；   
 * private LazyInnerClassSingleton(){
 *         if(LazyHolder.INSTANCE!=null)
 *         {
 *             throw new RuntimeException("不允许非法访问");
 *         }
 *     }
 * 缺点：不够优雅；
 *
 * 解决办法2：注册式单例
 *                  -->枚举式单例，可参照EnumSingleton
 */
public class LazyInnerClassSingleton
{
    private LazyInnerClassSingleton(){
        if(LazyHolder.INSTANCE!=null)
        {
            throw new RuntimeException("不允许非法访问");
        }
    }

    public static LazyInnerClassSingleton getInstance(){
        return LazyHolder.INSTANCE;
    }

    //默认不加载，当使用时才会加载
    public static class LazyHolder{
        private static final LazyInnerClassSingleton INSTANCE = new LazyInnerClassSingleton();
    }
}
