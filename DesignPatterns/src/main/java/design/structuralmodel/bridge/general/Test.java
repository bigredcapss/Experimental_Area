package design.structuralmodel.bridge.general;

/**
 * 桥接模式测试类
 * @author BigRedCaps
 * @date 2020/12/28 12:56
 */
public class Test
{
    public static void main (String[] args)
    {
        // 实现化角色对象
        IImplementor iImplementor = new ConcreteImplementorA();
        // 抽象化角色对象
        Abstraction abstraction = new RefinedAbstraction(iImplementor);
        abstraction.operation();
    }
}
