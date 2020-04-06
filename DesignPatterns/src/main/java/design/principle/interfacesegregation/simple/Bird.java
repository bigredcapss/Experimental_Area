package design.principle.interfacesegregation.simple;

/**
 * @Description:Bird不会游泳,这种接口的实现说明接口设计的不行
 * @Author:BigRedCaps
 */
public class Bird implements IAnimal
{
    @Override
    public void eat ()
    {

    }

    @Override
    public void fly ()
    {

    }

    @Override
    public void swim ()
    {

    }
}
