package design.structuralmodel.adapter.general.objectadapter;

/**
 * 适配器角色
 * @author BigRedCaps
 * @date 2020/12/27 21:32
 */
public class Adapter implements Target
{
    private Adaptee adaptee;

    public Adapter(Adaptee adaptee){
        this.adaptee = adaptee;
    }

    @Override
    public int request ()
    {
        return adaptee.specificRequest()/10;
    }
}
