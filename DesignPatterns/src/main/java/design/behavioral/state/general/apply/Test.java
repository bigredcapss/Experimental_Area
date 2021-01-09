package design.behavioral.state.general.apply;

/**
 * 状态模式测试类
 */
public class Test {
    public static void main(String[] args) {
        Context context = new Context();
        context.setState(new ConcreteStateA());
        context.handle();
    }
}
