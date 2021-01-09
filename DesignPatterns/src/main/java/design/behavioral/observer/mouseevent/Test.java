package design.behavioral.observer.mouseevent;


import design.behavioral.observer.mouseevent.handler.Mouse;
import design.behavioral.observer.mouseevent.handler.MouseEventLisenter;
import design.behavioral.observer.mouseevent.handler.MouseEventType;

/**
 * 使用观察者模式设计鼠标响应API
 */
public class Test {
    public static void main(String[] args) {
        MouseEventLisenter lisenter = new MouseEventLisenter();

        Mouse mouse = new Mouse();
        mouse.addLisenter(MouseEventType.ON_CLICK,lisenter);
        mouse.addLisenter(MouseEventType.ON_MOVE,lisenter);

        mouse.click();
        mouse.move();
    }
}
