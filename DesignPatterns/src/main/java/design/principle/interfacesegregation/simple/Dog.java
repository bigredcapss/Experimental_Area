package design.principle.interfacesegregation.simple;

/**
 * @Description:Dog不会飞，也不会游泳，这种接口的实现说明接口设计的不行
 * @Author:BigRedCaps
 */
public class Dog implements IAnimal
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
