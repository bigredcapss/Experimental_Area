package design.structuralmodel.decorator.general;

/**
 * @author BigRedCaps
 * @date 2020/12/30 22:17
 */
public class ConcreteDecoratorB extends Decorator
{
    /**
     * 构造方法，传入组件对象
     *
     * @param component 组件对象
     */
    public ConcreteDecoratorB(Component component) {
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
