package design.structuralmodel.bridge.general;

/**
 * 具体实现A
 * @author BigRedCaps
 * @date 2020/12/28 12:52
 */
public class ConcreteImplementorA implements IImplementor
{
    @Override
    public void operationImpl ()
    {
        System.out.println("I am ConcreteImplementorA");
    }
}
