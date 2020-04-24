package design.structuralmodel.proxy.staticproxy;

/**
 * 儿子张三没时间，张老三为儿子代理
 */
public class ZhangLaosan implements IPerson {

    private ZhangSan zhangsan;

    //这里：你是否会这样想，这里传一个IPerson接口不就行了吗，何必还要动态代理呢？
    //因为这里如果张老三要给自家的狗找对象，接口就不一样了，那你传IPerson接口不就不行了
    public ZhangLaosan(ZhangSan zhangsan) {
        this.zhangsan = zhangsan;
    }

    public void findLove() {
        System.out.println("张老三开始物色");
        zhangsan.findLove();
        System.out.println("开始交往");
    }

}
