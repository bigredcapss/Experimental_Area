package design.behavioral.observer.guava;

import com.google.common.eventbus.EventBus;

/**
 * 实现观察者模式非常好用的框架Guava测试
 */
public class Test {

    public static void main(String[] args) {
        EventBus eventBus = new EventBus();


        PojoEvent guavaEvent = new PojoEvent();
        VoEvent voEvent = new VoEvent();
        eventBus.register(guavaEvent);
        eventBus.register(voEvent);

        eventBus.post(new Pojo("Tom"));
    }
}
