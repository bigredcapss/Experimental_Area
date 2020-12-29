package design.structuralmodel.adapter.general.classadapter;

/**
 * 适配器角色
 * @author BigRedCaps
 * @date 2020/12/27 21:01
 */
public class Adapter extends Adaptee implements Target
{
    @Override
    public int request ()
    {
        return super.specificRequest()/10;
    }
}
