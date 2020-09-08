package generic;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:泛型化参数
 * @Author:BigRedCaps
 */
public class GenericParamDemo
{
    public static void main (String[] args) throws Exception
    {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("heel");
        list.add("study");

        // 通过反射去泛型化
        Class<? extends List> aClass = list.getClass();
        Method method = aClass.getDeclaredMethod("add", Object.class);
        // 在集合中加入Object
        method.invoke(list,new Object());
        System.out.println(list);
    }
}
