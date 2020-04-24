package design.creationmode.singleton.hungry;

/**
 * @Description:饿汉式单例模式
 * @Author:BigRedCaps
 * 优点：编写简单，线程安全；
 * 缺点：当HungrySingleton类中存在大量的单例对象时，由于单例对象在类加载的时候，就初始化了，导致大量的内存浪费；
 * 适用场景：适用于单例对象较少的情况；
 * 解决办法：懒汉式单例模式；
 */
public class HungrySingleton
{
    private static final HungrySingleton hungrySingleton = new HungrySingleton();

    private HungrySingleton(){}

    public static HungrySingleton getInstance(){
        return  hungrySingleton;
    }

}
