package design.behavioral.template.general;

/**
 * 模板方法模式测试
 * @author BigRedCaps
 * @date 2020/12/27 14:17
 */
public class Test
{
    public static void main (String[] args)
    {
        AbstractClass concreteClassA = new ConcreteClassA();
        concreteClassA.templateMethod();
        AbstractClass concreteClassB = new ConcreteClassB();
        concreteClassB.templateMethod();
    }
}
