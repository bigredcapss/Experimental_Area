package design.behavioral.mediator.general;

/**
 * 具体中介者角色---从具体的同事对象接收消息，向具体同事对象发出命令，协调各同事间的协作
 */
public class ConcreteMediator extends Mediator {
    @Override
    public void transferA() {
        // 协调行为:A 转发到 B
        this.colleagueB.selfMethodB();
    }

    @Override
    public void transferB() {
        // 协调行为:B 转发到 A
        this.colleagueA.selfMethodA();
    }
}