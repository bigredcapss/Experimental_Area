package design.behavioral.strategy.pay;

/**
 * 多种支付模式测试
 */

import design.behavioral.strategy.pay.payport.PayStrategy;

public class Test {
    public static void main(String[] args) {
        Order order = new Order("1","2020031401000323",324.5);
        System.out.println(order.pay(PayStrategy.UNION_PAY));
    }
}
