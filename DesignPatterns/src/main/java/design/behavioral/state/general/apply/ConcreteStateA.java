package design.behavioral.state.general.apply;

//具体状态类
// 具体状态类
public class ConcreteStateA extends State {
    @Override
    public void handle() {
        System.out.println("StateA do action");
        // A状态完成后自动切换到B状态
        this.context.setState(Context.STATE_B);
        this.context.getState().handle();
    }
}