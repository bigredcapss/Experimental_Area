package design.structuralmodel.adapter.general.interfaceadapter;

/**
 * 适配器角色
 * @author BigRedCaps
 * @date 2020/12/27 21:13
 */
public abstract class Adapter implements Target
{
    protected Adaptee adaptee;

    public Adapter(Adaptee adaptee){
        this.adaptee = adaptee;
    }
    @Override
    public int request1 ()
    {
        return 0;
    }

    @Override
    public int request2 ()
    {
        return 0;
    }

    @Override
    public int request3 ()
    {
        return 0;
    }

    @Override
    public int request4 ()
    {
        return 0;
    }
}
