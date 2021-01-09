package design.behavioral.mediator.general;

/**
 * 具体同事类角色---负责实现自发行为（Self-Method），转发依赖方法（Dep-Method)交由中介者协调
 */
public class ConcreteColleagueA extends Colleague {
    public ConcreteColleagueA(Mediator mediator) {
        super(mediator);
        this.mediator.setColleageA(this);
    }

    // 自有方法：self-Method
    public void selfMethodA() {
        // 处理自己的逻辑
        System.out.println(String.format("%s:self-Method", this.getClass().getSimpleName()));
    }

    // 依赖方法：dep-Method
    public void depMethodA() {
        // 处理自己的逻辑
        System.out.println(String.format("%s:depMethod: delegate to Mediator", this.getClass().getSimpleName()));
        // 无法处理的业务逻辑委托给中介者处理
        this.mediator.transferA();
    }
}