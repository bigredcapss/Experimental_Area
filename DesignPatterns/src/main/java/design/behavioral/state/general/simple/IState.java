package design.behavioral.state.general.simple;

/**
 * 抽象状态角色---定义该状态下的行为，可以有一个或多个行为
 */
public interface IState {
    void handle ();
}