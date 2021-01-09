package design.behavioral.state.general.simple;

/**
 * 状态模式测试
 */
public class Test {
    public static void main(String[] args) {
        Context context = new Context();
        context.setState(new ConcreteStateB());
        context.handle();
    }
}
