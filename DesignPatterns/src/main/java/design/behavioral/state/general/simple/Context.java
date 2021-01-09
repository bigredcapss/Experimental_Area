package design.behavioral.state.general.simple;

/**
 * 环境类角色---定义客户端需要的接口，内部维护一个当前状态实例，并负责具体状态的切换
 */
public class Context {
    private static final IState STATE_A = new ConcreteStateA();
    private static final IState STATE_B = new ConcreteStateB();
    //默认状态A
    private IState currentState = STATE_A;

    public void setState(IState state) {
        this.currentState = state;
    }

    public void handle() {
        this.currentState.handle();
    }
}