package design.behavioral.mediator.general;

/**
 * 抽象中介者---定义统一的接口，用于各同事角色之间的通信
 */
public abstract class Mediator {
    // 具体同事类对象
    protected ConcreteColleagueA colleagueA;
    protected ConcreteColleagueB colleagueB;

    public void setColleageA(ConcreteColleagueA colleague) {
        this.colleagueA = colleague;
    }

    public void setColleageB(ConcreteColleagueB colleague) {
        this.colleagueB = colleague;
    }

    // 中介者业务逻辑
    public abstract void transferA();

    public abstract void transferB();
}