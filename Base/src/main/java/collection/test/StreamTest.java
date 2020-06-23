package collection.test;

import collection.bean.Person;
import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @Description:Stream的collect方法的数据聚合操作
 * @Author:BigRedCaps
 */

/**
 * guava包里面有很多的工具类，Lists和Maps集合工具，集合操作做了些优化提升；
 * 具体用法见：Lists.newArrayList()与Maps.newLinkedHashMap()等
 */
public class StreamTest
{
    public static void main (String[] args)
    {

        //示例一:collect()方法的简单使用1
        Integer[] integers = Lists.newArrayList(1, 2, 3, 4, 5)
                .stream()
                .collect(() -> new Integer[]{0}, (a, x) -> a[0] += x, (a1, a2) -> a1[0] += a2[0]);
        Arrays.asList(integers).forEach(System.out::println);

        //示例一:collect()方法的简单使用2
        HashMap<Integer,List<Person>> maps = Lists.<Person>newArrayList().stream()
                .collect(() -> new HashMap<Integer, List<Person>>(),
                        (h, x) -> {
                            List<Person> value = h.getOrDefault(x.getType(), Lists.newArrayList());
                            value.add(x);
                            h.put(x.getType(), value);
                        },
                        HashMap::putAll
                );
        System.out.println(maps);



    }
}
