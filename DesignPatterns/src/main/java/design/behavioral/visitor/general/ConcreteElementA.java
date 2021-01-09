package design.behavioral.visitor.general;

// 具体元素
public class ConcreteElementA implements IElement {

    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

    public String operationA() {
        return this.getClass().getSimpleName();
    }

}