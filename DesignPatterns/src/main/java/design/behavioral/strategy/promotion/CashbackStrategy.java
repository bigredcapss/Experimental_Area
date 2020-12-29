package design.behavioral.strategy.promotion;

/**
 * 返现策略
 * @author BigRedCaps
 * @date 2020/12/29 23:01
 */
public class CashbackStrategy implements IPromotionStrategy
{
    @Override
    public void doPromotion ()
    {
        System.out.println("返现，直接打款到支付宝账号");
    }
}
