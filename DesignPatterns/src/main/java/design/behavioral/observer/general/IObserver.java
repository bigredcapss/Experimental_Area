package design.behavioral.observer.general;

/**
 * 抽象观察者角色---定义了响应通知的更新方法
 * @author BigRedCaps
 * @date 2021/1/8 20:46
 */
public interface IObserver<E>
{
    void update(E event);
}
