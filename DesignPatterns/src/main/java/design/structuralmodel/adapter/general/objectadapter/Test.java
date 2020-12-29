package design.structuralmodel.adapter.general.objectadapter;

/**
 * 对象适配者模式测试
 * @author BigRedCaps
 * @date 2020/12/27 21:42
 */
public class Test
{
    public static void main (String[] args)
    {
        Target target = new Adapter(new Adaptee());
        int result = target.request();
        System.out.println(result);
    }
}
