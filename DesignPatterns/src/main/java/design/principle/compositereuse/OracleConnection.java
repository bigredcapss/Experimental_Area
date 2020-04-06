package design.principle.compositereuse;

/**
 * @Description:
 * @Author:BigRedCaps
 */
public class OracleConnection extends DBConnection
{
    @Override
    public String getConnection ()
    {
        return "Oracle数据库连接";
    }
}
