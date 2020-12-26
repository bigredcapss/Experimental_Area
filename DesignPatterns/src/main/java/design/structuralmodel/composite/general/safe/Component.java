package design.structuralmodel.composite.general.safe;

/**
 * 安全组合模式--组合类(抽象根节点)
 * @author BigRedCaps
 * @date 2020/12/26 15:30
 */
public abstract class Component
{
    protected String name;

    public Component (String name)
    {
        this.name = name;
    }

    public abstract String operation();
}
