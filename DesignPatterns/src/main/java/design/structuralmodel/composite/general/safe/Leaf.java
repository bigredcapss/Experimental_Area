package design.structuralmodel.composite.general.safe;

/**
 * 安全组合模式--叶子节点
 * @author BigRedCaps
 * @date 2020/12/26 15:37
 */
public class Leaf extends Component
{
    public Leaf (String name)
    {
        super(name);
    }

    @Override
    public String operation ()
    {
        return this.name;
    }
}
