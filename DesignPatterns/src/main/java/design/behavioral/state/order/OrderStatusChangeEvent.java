package design.behavioral.state.order;

/**
 * 订单状态改变事件枚举类
 */
public enum OrderStatusChangeEvent {
    // 支付，发货，确认收货
    PAYED, DELIVERY, RECEIVED;
}