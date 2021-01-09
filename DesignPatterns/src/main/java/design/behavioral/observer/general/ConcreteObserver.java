package design.behavioral.observer.general;

/**
 * 具体观察者角色---在得到状态更新时，会自动做出响应
 * @author BigRedCaps
 * @date 2021/1/8 20:55
 */
public class ConcreteObserver<E> implements IObserver<E>
{
    @Override
    public void update (E event)
    {
        System.out.println("receive event:"+event);
    }
}
