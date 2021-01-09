package design.structuralmodel.facade.points;

/**
 * 积分兑换礼品的商城demo测试
 */
public class Test {

    public static void main(String[] args) {

        FacadeService facadeService = new FacadeService();

        GiftInfo giftInfo = new GiftInfo("华为Mate30");

        facadeService.exchange(giftInfo);

    }

}
