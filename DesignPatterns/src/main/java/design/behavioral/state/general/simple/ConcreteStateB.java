package design.behavioral.state.general.simple;

/**
 * 具体状态角色---具体实现该状态对应的行为，并且在需要的情况下，进行状态切换
 */
public class ConcreteStateB implements IState {
    public void handle() {
        //必要时刻需要进行状态切换
        System.out.println("StateB do action");
    }
}