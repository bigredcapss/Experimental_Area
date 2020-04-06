package design.principle.liskovsubstitution.methodreturn;

/**
 * @Description:
 * @Author:BigRedCaps
 */
public class MethodReturnTest
{
    public static void main (String[] args)
    {
        Parent child = new Child();

        System.out.println(child.method());
    }
}
