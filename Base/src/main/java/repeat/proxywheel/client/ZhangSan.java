package repeat.proxywheel.client;

/**
 * @Description:
 * @Author:BigRedCaps
 */
public class ZhangSan implements IPerson
{
    @Override
    public void findlove ()
    {
        System.out.println("张三要求：肤白貌美大长腿");
    }

    @Override
    public void buyInsure ()
    {
        System.out.println("30万保险");
    }
}
