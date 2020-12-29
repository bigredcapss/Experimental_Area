package design.behavioral.strategy.promotion;

/**
 * 无优惠策略
 * @author BigRedCaps
 * @date 2020/12/29 23:04
 */
public class EmptyStrategy implements IPromotionStrategy
{
    @Override
    public void doPromotion() {
        System.out.println("无优惠");
    }
}
