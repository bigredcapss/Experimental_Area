package design.behavioral.observer.guava;

import com.google.common.eventbus.Subscribe;


/**
 * 创建侦听事件VoEvent
 */
public class VoEvent {

    @Subscribe
    public void observer(Vo arg){
//        if(arg instanceof Vo){
            System.out.println("执行VoEvent方法，传参为：" + arg);
//        }

    }

}
