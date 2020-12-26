package design.creationmode.flyweight.general;

/**
 * 具体享元角色
 * @author BigRedCaps
 * @date 2020/12/26 14:13
 */
public class ConcreteFlyweight implements IFlyweight
{
    private String intrinsicState;

    public ConcreteFlyweight(String intrinsicState){
        this.intrinsicState = intrinsicState;
    }

    @Override
    public void operation (String extrinsicState)
    {
        System.out.println("Object address:"+System.identityHashCode(this));
        System.out.println("IntrinsicStete:"+this.intrinsicState);
        System.out.println("ExtrinsicState:"+extrinsicState);
    }
}
