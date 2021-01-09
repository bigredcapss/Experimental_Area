package design.behavioral.memento.general;

/**
 * 发起人角色---负责创建一个备忘录，记录自身需要保存的状态，具备状态回滚功能
 */
public class Originator {
    // 内部状态
    private String state;

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    // 创建一个备忘录
    public Memento createMemento() {
        return new Memento(this.state);
    }

    // 从备忘录恢复
    public void restoreMemento(Memento memento) {
        this.setState(memento.getState());
    }
}