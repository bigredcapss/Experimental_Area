package design.behavioral.visitor.general;

import java.util.ArrayList;
import java.util.List;

// 结构对象
public class ObjectStructure {
    private List<IElement> list = new ArrayList<IElement>();

    {
        this.list.add(new ConcreteElementA());
        this.list.add(new ConcreteElementB());
    }

    public void accept(IVisitor visitor) {
        for (IElement element : this.list) {
            element.accept(visitor);
        }
    }
}