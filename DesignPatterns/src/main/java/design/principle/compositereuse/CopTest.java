package design.principle.compositereuse;

/**
 * @Description:
 * @Author:BigRedCaps
 */
public class CopTest
{
    public static void main (String[] args)
    {
        ProductDao productDao = new ProductDao();
        productDao.setConnection(new MySqlConnection());
        productDao.addProduct();
    }
}
