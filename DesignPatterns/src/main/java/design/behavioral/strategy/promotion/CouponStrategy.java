package design.behavioral.strategy.promotion;

/**
 * 优惠券策略
 * @author BigRedCaps
 * @date 2020/12/29 23:02
 */
public class CouponStrategy implements IPromotionStrategy
{
    @Override
    public void doPromotion() {
        System.out.println("使用优惠券抵扣");
    }
}
