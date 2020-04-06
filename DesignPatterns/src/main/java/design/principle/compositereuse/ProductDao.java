package design.principle.compositereuse;

/**
 * @Description:
 * @Author:BigRedCaps
 */
public class ProductDao
{
    private DBConnection dbConnection;
    
    public void setConnection(DBConnection dbConnection)
    {
        this.dbConnection = dbConnection;
    }
    
    public void addProduct()
    {
        String conn = dbConnection.getConnection();
        System.out.println("使用"+conn+"增加产品");
    }
}
