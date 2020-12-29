package design.behavioral.strategy.promotion;

/**
 * 上下文环境
 * @author BigRedCaps
 * @date 2020/12/29 22:58
 */
public class PromotionActivity
{
    private IPromotionStrategy iPromotionStrategy;

    public PromotionActivity(IPromotionStrategy iPromotionStrategy){
        this.iPromotionStrategy = iPromotionStrategy;
    }

    public void execute(){
        iPromotionStrategy.doPromotion();
    }
}
