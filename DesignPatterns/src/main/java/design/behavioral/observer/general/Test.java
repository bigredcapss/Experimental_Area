package design.behavioral.observer.general;

/**
 * 观察者模式测试类
 * @author BigRedCaps
 * @date 2021/1/8 20:59
 */
public class Test
{
    public static void main (String[] args)
    {
        // 被观察者
        ISubject<String> observable = new ConcreteSubject<>();
        // 观察者
        IObserver<String> observer = new ConcreteObserver<>();
        // 注册
        observable.attach(observer);
        // 通知
        observable.notify("hello");

    }
}
