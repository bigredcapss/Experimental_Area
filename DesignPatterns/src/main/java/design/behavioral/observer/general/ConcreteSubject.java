package design.behavioral.observer.general;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体主题角色---具体被观察者，当其内状态变化时，会通知已注册的观察者
 * @author BigRedCaps
 * @date 2021/1/8 20:49
 */
public class ConcreteSubject<E> implements ISubject<E>
{
    private List<IObserver<E>> observers = new ArrayList<>();

    @Override
    public boolean attach (IObserver<E> observer)
    {
        return !this.observers.contains(observer) && observers.add(observer);
    }

    @Override
    public boolean detach (IObserver<E> observer)
    {
        return this.observers.remove(observer);
    }

    @Override
    public void notify (E event)
    {
        for (IObserver<E> observer:this.observers){
            observer.update(event);
        }
    }
}
