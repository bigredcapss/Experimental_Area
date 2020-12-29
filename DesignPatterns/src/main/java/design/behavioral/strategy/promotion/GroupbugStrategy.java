package design.behavioral.strategy.promotion;

/**
 * 拼团策略
 * @author BigRedCaps
 * @date 2020/12/29 23:03
 */
public class GroupbugStrategy implements IPromotionStrategy
{
    @Override
    public void doPromotion() {
        System.out.println("5人成团，可以优惠");
    }
}
