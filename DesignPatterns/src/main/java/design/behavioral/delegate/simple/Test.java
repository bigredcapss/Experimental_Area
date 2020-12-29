package design.behavioral.delegate.simple;

/**
 * 委派模式测试类
 * @author BigRedCaps
 * @date 2020/12/27 14:00
 */
public class Test
{
    public static void main (String[] args)
    {
        new Boss().command("海报图",new Leader());
        new Boss().command("爬虫",new Leader());
        new Boss().command("卖手机",new Leader());
    }
}
