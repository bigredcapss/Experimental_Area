package design.structuralmodel.bridge.general;

/**
 * 抽象角色
 * @author BigRedCaps
 * @date 2020/12/27 21:50
 */
public abstract class Abstraction
{
    protected IImplementor iImplementor;

    public Abstraction(IImplementor iImplementor){
        this.iImplementor = iImplementor;
    }

    public void operation(){
        this.iImplementor.operationImpl();
    }

}
