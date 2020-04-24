package design.creationmode.builder.general;

/**
 * @Description:客户验收房子
 * @Author:BigRedCaps
 */
public class Director
{
    public static void main (String[] args)
    {
        IBuilder builder =  new ConcreteBuilder();

        System.out.println(builder.build());

    }
}
