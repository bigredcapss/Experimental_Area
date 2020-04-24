package design.creationmode.builder.general;

/**
 * @Description:建造者建造房子的过程
 * @Author:BigRedCaps
 */
public class ConcreteBuilder implements IBuilder
{
    private Product product = new Product();

    @Override
    public Product build ()
    {
        return product;
    }
}
