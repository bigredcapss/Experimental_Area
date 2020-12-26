package design.structuralmodel.composite.general.transparent;

import java.util.ArrayList;
import java.util.List;

/**
 *  透明组合模式--树枝节点
 * @author BigRedCaps
 * @date 2020/12/26 15:16
 */
public class Composite extends Component
{
    List<Component> mComponents;

    public Composite (String name)
    {
        super(name);
        this.mComponents = new ArrayList<>();
    }

    @Override
    public String operation ()
    {
        StringBuilder stringBuilder = new StringBuilder(this.name);
        for (Component component:mComponents)
        {
            stringBuilder.append("\n");
            stringBuilder.append(component.operation());
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean addChild (Component component)
    {
        return this.mComponents.add(component);
    }

    @Override
    public boolean removeChild (Component component)
    {
        return this.mComponents.remove(component);
    }

    @Override
    public Component getChild (int index)
    {
        return this.mComponents.get(index);
    }
}
