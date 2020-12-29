package design.behavioral.strategy.general;

/**
 * 具体策略类A
 * @author BigRedCaps
 * @date 2020/12/28 18:25
 */
public class ConcreteStrategyA implements IStrategy
{
    @Override
    public void algorithm ()
    {
        System.out.println("algorithm A");
    }
}
