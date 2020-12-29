package design.structuralmodel.bridge.general;

/**
 * 具体实现B
 * @author BigRedCaps
 * @date 2020/12/28 12:54
 */
public class ConcreteImplementorB implements IImplementor
{
    @Override
    public void operationImpl ()
    {
        System.out.println("I am ConcreteImplementorB");
    }
}
