package design.creationmode.singleton.hungry;

/**
 * @Description:饿汉式单例模式--装B写法
 * @Author:BigRedCaps
 */
public class HungryStaticSingleton
{
    private static final HungryStaticSingleton hungrySingleton;

    static {
        hungrySingleton = new HungryStaticSingleton();
    }

    private HungryStaticSingleton(){}

    public static HungryStaticSingleton getInstance(){
        return hungrySingleton;
    }

}
