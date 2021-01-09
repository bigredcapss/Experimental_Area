package design.behavioral.memento.general;

/**
 * 备忘录管理员角色---负责存储，提供管理备忘录，无法对备忘录内容进行操作和访问
 */
public class Caretaker {
    // 备忘录对象
    private Memento memento;

    public Memento getMemento() {
        return this.memento;
    }

    public void storeMemento(Memento memento) {
        this.memento = memento;
    }

}