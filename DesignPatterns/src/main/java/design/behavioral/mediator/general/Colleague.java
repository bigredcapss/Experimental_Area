package design.behavioral.mediator.general;

/**
 * 抽象同事类---每一个同事对象均需要依赖中介者角色，与其它同事间通信时，交由中介者进行转发协作
 */
public abstract class Colleague {
    // 中介者对象
    protected Mediator mediator;

    public Colleague(Mediator mediator) {
        this.mediator = mediator;
    }
}