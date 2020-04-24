package design.creationmode.builder.general;

import lombok.Data;

/**
 * @Description:要构建的一个对象--施工队（建造者）要交付的房子
 * @Author:BigRedCaps
 */
@Data
public class Product
{
    private String name;

    @Override
    public String toString ()
    {
        return "Product{" + "name='" + name + '\'' + '}';
    }
}
