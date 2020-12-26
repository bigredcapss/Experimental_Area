package design.structuralmodel.composite.general.safe;

import java.util.ArrayList;
import java.util.List;

/**
 * 安全组合模式--树枝节点
 * @author BigRedCaps
 * @date 2020/12/26 15:32
 */
public class Composite extends Component
{
    private List<Component> mComponents;

    public Composite (String name)
    {
        super(name);
        this.mComponents = new ArrayList<Component>();
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

    public boolean addChild(Component component) {
        return this.mComponents.add(component);
    }

    public boolean removeChild(Component component) {
        return this.mComponents.remove(component);
    }

    public Component getChild(int index) {
        return this.mComponents.get(index);
    }


}
