package design.creationmode.singleton.register;

/**
 * @Description:枚举式单例
 * @Author:BigRedCaps
 *
 * 枚举式单例作为注册式单例的一种
 * 注册式单例：将每一个单例都缓存到统一的容器中，使用唯一的标识获取单例；
 *
 * 优点：解决了线程安全问题；
 * 缺点：造成了内存的浪费；同时序列化依然会破坏枚举式单例
 */
public enum EnumSingleton
{
    //这里在一开始就把单例作为一个常量放在了枚举对应的Map集合中，通过key唯一标识一个对象，可以查看（枚举）源码
    //枚举的这种结构也避免了线程安全的问题
    INSTANCE;

    //为枚举单例设置值
    private Object data;

    public Object getData ()
    {
        return data;
    }

    public void setData (Object data)
    {
        this.data = data;
    }

    public static EnumSingleton getInstance(){
        return INSTANCE;
    }
}
