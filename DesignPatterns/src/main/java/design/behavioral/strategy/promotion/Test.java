package design.behavioral.strategy.promotion;

/**
 * 促销策略测试
 * @author BigRedCaps
 * @date 2020/12/29 23:10
 */
public class Test
{
    public static void main(String[] args) {
//        IPromotionStrategy strategy = null;

//        PromotionActivity activity = new PromotionActivity(new GroupbuyStrategy());
//        activity.execute();
        PromotionStrategyFacory.getPromotionKeys();
        String promotionKey = "COUPON";

        IPromotionStrategy promotionStrategy = PromotionStrategyFacory.getPromotionStrategy(promotionKey);
        promotionStrategy.doPromotion();
    }
}
