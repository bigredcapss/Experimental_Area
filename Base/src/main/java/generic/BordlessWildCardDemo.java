package generic;


import java.util.ArrayList;
import java.util.List;

/**
 * @Description:无边界通配符
 * @Author:BigRedCaps
 */
public class BordlessWildCardDemo
{
    public static void main (String[] args)
    {
        List<String> list = new ArrayList<>();
        list.add("hello");
        list.add("love");
        list.add("destry");
    }
    // 无边界的通配符
    public static void loop(List<?> list){
        for (int i = 0; i < list.size(); i++)
        {
            System.out.println(list.get(i));
        }
    }
}
