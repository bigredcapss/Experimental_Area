package design.principle.compositereuse;

/**
 * @Description:
 * @Author:BigRedCaps
 */
public class MySqlConnection extends DBConnection
{
    @Override
    public String getConnection ()
    {
        return "MySql数据库连接";
    }
}
