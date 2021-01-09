package design.behavioral.visitor.general;

import java.util.Random;

// 具体元素
public class ConcreteElementB implements IElement {

    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

    public int operationB() {
        return new Random().nextInt(100);
    }
}