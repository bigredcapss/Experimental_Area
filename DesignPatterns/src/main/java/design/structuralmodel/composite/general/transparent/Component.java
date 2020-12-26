package design.structuralmodel.composite.general.transparent;

/**
 * 组合模式的透明写法--组合类（抽象根节点）
 * @author BigRedCaps
 * @date 2020/12/26 15:07
 */
public abstract class Component
{
    protected String name;

    public Component (String name)
    {
        this.name = name;
    }

    public abstract String operation();

    public boolean addChild(Component component){
        throw new UnsupportedOperationException("addChild not support!");
    }

    public boolean removeChild(Component component){
        throw new UnsupportedOperationException("removeChild not support!");
    }

    public Component getChild(int index){
        throw new UnsupportedOperationException("getChild not support!");
    }


}
