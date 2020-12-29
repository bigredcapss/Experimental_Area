package design.behavioral.strategy.promotion;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 促销策略工厂
 * 注：为了便于后期出现更多类型的促销活动，这里结合单例模式和工厂模式，才有了PromotionStrategyFacory类
 * @author BigRedCaps
 * @date 2020/12/29 23:09
 */
public class PromotionStrategyFacory
{

    private static Map<String,IPromotionStrategy> PROMOTIONS = new HashMap<>();

    static {
        PROMOTIONS.put(PromotionKey.COUPON,new CouponStrategy());
        PROMOTIONS.put(PromotionKey.CASHBACK,new CashbackStrategy());
        PROMOTIONS.put(PromotionKey.GROUPBUY,new GroupbugStrategy());
    }

    private static final IPromotionStrategy EMPTY = new EmptyStrategy();

    private PromotionStrategyFacory (){}

    public static IPromotionStrategy getPromotionStrategy(String promotionKey){
        IPromotionStrategy strategy = PROMOTIONS.get(promotionKey);
        return strategy == null ? EMPTY : strategy;
    }
    private interface PromotionKey{
        String COUPON = "COUPON";
        String CASHBACK = "CASHBACK";
        String GROUPBUY = "GROUPBUY";
    }

    public static  Set<String> getPromotionKeys(){
        return PROMOTIONS.keySet();
    }
}
