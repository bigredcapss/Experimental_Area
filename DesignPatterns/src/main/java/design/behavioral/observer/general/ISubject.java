package design.behavioral.observer.general;

/**
 * 抽象主题角色---指被观察的对象（Observable）。该角色是一个抽象类或者接口，定义了删除，增加，通知观察者对象的方法
 * @author BigRedCaps
 * @date 2021/1/8 20:43
 */
public interface ISubject<E>
{
    boolean attach(IObserver<E> observer);

    boolean detach(IObserver<E> observer);

    void notify(E event);
}
