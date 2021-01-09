package design.behavioral.state.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 利用状态机实现订单状态流转控制
 *
 * 状态机也是状态模式的一种应用，相当于上下文角色的一个升级版。在工作流或游戏等各种系统中有大量使用，如各种工作流引擎，它
 * 几乎是状态机的子集和实现，封装状态的变化规则。Spring也提供给了我们一个很好的解决方案。Spring中的组件名称就叫
 * StateMachine(状态机)。状态机帮助开发者简化状态控制的开发过程，让状态机结构更加层次化。该例子使用Spring状态机模拟了
 * 订单状态流转的过程。
 */
@SpringBootApplication
public class Test {
    public static void main(String[] args) {

        Thread.currentThread().setName("主线程");

        ConfigurableApplicationContext context = SpringApplication.run(Test.class,args);

        IOrderService orderService = (IOrderService)context.getBean("orderService");

        orderService.create();
        orderService.create();

        orderService.pay(1);

        new Thread("客户线程"){
            @Override
            public void run() {
                orderService.deliver(1);
                orderService.receive(1);
            }
        }.start();

        orderService.pay(2);
        orderService.deliver(2);
        orderService.receive(2);

        System.out.println("全部订单状态：" + orderService.getOrders());

    }
}
