package design.principle.interfacesegregation.segregation;

/**
 * @Description:
 * @Author:BigRedCaps
 */
public class Dog implements IEatAnimal
{
    @Override
    public void eat ()
    {
        System.out.println("Dog is eatting");
    }
}
