package design.behavioral.iterator.general;

/**
 * 迭代器模式测试类
 * @author BigRedCaps
 * @date 2021/1/3 15:46
 */
public class Test
{
    public static void main (String[] args)
    {
        //来一个容器对象
        IAggregate<String> aggregate = new ConcreteAggregate<String>();
        //添加元素
        aggregate.add("one");
        aggregate.add("two");
        aggregate.add("three");
        //获取容器对象迭代器
        Iterator<String> iterator = aggregate.iterator();
        //遍历
        while (iterator.hasNext()) {
            String element = iterator.next();
            System.out.println(element);
        }

    }
}
