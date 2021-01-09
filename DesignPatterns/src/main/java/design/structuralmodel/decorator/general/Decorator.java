package design.structuralmodel.decorator.general;

/**
 * 抽象装饰器（Decorator）--是通用的装饰ConcreteComponent的装饰器,其内部必然有一个属性指向Component抽象组件；
 * 其实现一般是一个抽象类，主要是为了让其子类按照其构造形式传入一个Component抽象组件，这是强制的通用行为（当然如果系统中装饰逻辑单一
 *  ，并不需要实现许多装饰器，我们可以直接省略该类，而直接实现一个具体装饰器（ConcreteDecorator）即可）
 * @author BigRedCaps
 * @date 2020/12/30 22:06
 */
public abstract class Decorator extends Component
{
    /**
     * 持有组件对象
     */
    protected Component component;

    /**
     * 构造方法，传入组件对象
     * @param component 组件对象
     */
    public Decorator(Component component) {
        this.component = component;
    }

    public void operation() {
        //转发请求给组件对象，可以在转发前后执行一些附加动作
        component.operation();
    }
}
