package design.structuralmodel.decorator.general;

/**
 * 具体装饰器(ConcreteDecorator)--抽象装饰器（Decorator）的实现类,理论上，每个ConcreteDecorator都扩展了Component对象的
 * 一种功能
 *
 *
 * 注：抽象装饰器（Decorator）--是通用的装饰ConcreteComponent的装饰器,其内部必然有一个属性指向Component抽象组件；其实现一般
 * 是一个抽象类，主要是为了让其子类按照其构造形式传入一个Component抽象组件，这是强制的通用行为（当然如果系统中装饰逻辑单一
 * ，并不需要实现许多装饰器，我们可以直接省略该类，而直接实现一个具体装饰器（ConcreteDecorator）即可）
 *
 * @author BigRedCaps
 * @date 2020/12/30 21:57
 */
public class ConcreteDecoratorA extends Decorator
{
    /**
     * 构造方法，传入组件对象
     *
     * @param component 组件对象
     */
    public ConcreteDecoratorA(Component component) {
        super(component);
    }

    private void operationFirst(){ } //在调用父类的operation方法之前需要执行的操作
    private void operationLast(){ } //在调用父类的operation方法之后需要执行的操作

    public void operation() {
        //调用父类的方法，可以在调用前后执行一些附加动作
        operationFirst(); //添加的功能
        super.operation();  //这里可以选择性的调用父类的方法，如果不调用则相当于完全改写了方法，实现了新的功能
        operationLast(); //添加的功能
    }
}
