package design.creationmode.singleton.register;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description:springioc中的改善的容器式单例
 * @Author:BigRedCaps
 *
 * 注册式单例：将每一个单例都缓存到统一的容器中，使用唯一的标识获取单例；
 * 这种容器式单例适合创建大量的单例对象
 *
 * 优点：避免了内存浪费
 * 缺点：会有线程安全问题;
 *
 * 解决办法：加锁（简单加锁或者双重检查锁都可以）
 */
public class ContainerSingleton
{
    private ContainerSingleton(){}

    private static Map<String,Object> ioc = new ConcurrentHashMap<>();

    //将单例对象缓存到统一的容器中
    public static Object getInstance(String className){
        synchronized (ioc){
            Object instance = null;
            if(!ioc.containsKey(className))
            {
                try
                {
                    instance = Class.forName(className).newInstance();
                    ioc.put(className,instance);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
                return instance;
            }else {
                return ioc.get(className);
            }
        }

    }
}
