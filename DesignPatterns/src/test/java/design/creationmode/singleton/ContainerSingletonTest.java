package design.creationmode.singleton;

import design.creationmode.singleton.register.ContainerSingleton;

/**
 * @Description:容器式单例测试
 * @Author:BigRedCaps
 */
public class ContainerSingletonTest
{
    public static void main (String[] args)
    {
        Object instance1 = ContainerSingleton.getInstance("design.creationmode.singleton.lazy.POJO");
        Object instance2 =  ContainerSingleton.getInstance("design.creationmode.singleton.lazy.POJO");
        System.out.println(instance1==instance2);
    }
}
