package design.creationmode.flyweight.general;

/**
 * 享元模式测试类
 * @author BigRedCaps
 * @date 2020/12/26 14:22
 */
public class Test
{
    public static void main (String[] args)
    {
        IFlyweight flyweight = FlyweightFactory.getFlyweight("aa");
        flyweight.operation("a");
    }


}
