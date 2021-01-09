package design.structuralmodel.facade.general;

/**
 * 门面模式测试类
 * @author BigRedCaps
 * @date 2020/12/30 20:42
 */
public class Test
{
    public static void main (String[] args)
    {
        Facade facade = new Facade();
        facade.doA();
        facade.doB();
        facade.doC();
    }
}
