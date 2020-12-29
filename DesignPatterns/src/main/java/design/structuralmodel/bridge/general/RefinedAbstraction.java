package design.structuralmodel.bridge.general;

/**
 * 修正抽象角色
 * @author BigRedCaps
 * @date 2020/12/28 12:55
 */
public class RefinedAbstraction extends Abstraction
{
    public RefinedAbstraction (IImplementor iImplementor)
    {
        super(iImplementor);
    }

    @Override
    public void operation ()
    {
        super.operation();
        System.out.println("refined operation");
    }
}
