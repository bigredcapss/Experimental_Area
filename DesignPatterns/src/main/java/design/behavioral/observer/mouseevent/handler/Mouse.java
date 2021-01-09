package design.behavioral.observer.mouseevent.handler;


import design.behavioral.observer.mouseevent.core.EventContext;

/**
 * 具体的被观察者
 */
public class Mouse extends EventContext
{
    public void click(){
        System.out.println("调用单击方法");
        this.trigger(MouseEventType.ON_CLICK);
    }

    public void move(){
        System.out.println("调用移动方法");
        this.trigger(MouseEventType.ON_MOVE);
    }
}
