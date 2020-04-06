package design.principle.interfacesegregation.segregation;

/**
 * @Description:
 * @Author:BigRedCaps
 */
public class Bird implements IEatAnimal,IFlyAnimal
{
    @Override
    public void eat ()
    {
        System.out.println("Bird is eatting");
    }

    @Override
    public void fly ()
    {
        System.out.println("Bird is flying");
    }
}
