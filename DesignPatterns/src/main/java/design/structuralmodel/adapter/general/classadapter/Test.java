package design.structuralmodel.adapter.general.classadapter;

/**
 * 适配器模式测试类
 * 类适配器：通过继承来实现适配器的功能，具体做法：让Adapter继承Adaptee,并实现Target（目标接口），这样适配器（Adapter）
 * 就具备了Target和Adaptee的特性，就可以将两者进行转化；
 * @author BigRedCaps
 * @date 2020/12/27 21:05
 */
public class Test
{
    public static void main (String[] args)
    {
        Target target = new Adapter();
        int request = target.request();
        System.out.println(request);
    }
}
