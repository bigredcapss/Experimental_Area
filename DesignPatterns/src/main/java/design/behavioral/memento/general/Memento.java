package design.behavioral.memento.general;

/**
 * 备忘录角色---用于存储Originator的内部状态，且可以防止Originator以外的对象进行访问
 */
public class Memento {
    private String state;

    public Memento(String state){
        this.state = state;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }
}