package collection.test;

import java.util.Collection;
import java.util.HashSet;
import java.util.function.Predicate;
import java.util.stream.IntStream;

/**
 * @Description:Java8操作集合
 * @Example:IntStream
 * @Author:BigRedCaps
 */
public class CollectionOperation
{
    public static void main (String[] args)
    {
        Collection books = new HashSet();
        books.add("语文");
        books.add("数学");
        books.add("英语");
        books.add("政治");
        books.add("文理科");
        books.add("疯狂Java讲义");
        books.add("疯狂Android讲义");
        books.add("疯狂AJax讲义");
        books.add("疯狂IOS讲义");
        System.out.println("********************Lambda表达式（目标类型为Customer类型）迭代集合元素：********************");
        books.forEach(obj-> System.out.println(obj));
        System.out.println("********************Lambda表达式（目标类型为Predicate类型）过滤集合元素：********************");
//        books.removeIf(obj->((String)obj).length()>2);
//        System.out.println(books);
        System.out.println(calAll(books,ele->((String)ele).contains("疯狂")));
        System.out.println("********************Java8新增的Stream操作集合元素：********************");
        IntStream intStream = IntStream.builder().add(20)
                .add(12).add(-2).add(15).build();
        //下面的打印语句方法每次只能执行一行
//        System.out.println("intStream所有元素的最大值："+intStream.max().getAsInt());
//        System.out.println("intStream所有元素的平均值："+intStream.average());
//        System.out.println("intStream所有元素的总和:"+intStream.sum());
//        System.out.println("intStream所有元素的平方是否都大于20"+intStream.allMatch(ele->ele*ele>20));
        //将intStream映射为一个新的IntStream,新的IntStream的每个元素是原来元素的2倍+1
        IntStream is = intStream.map(ele->ele*2+1);
        //使用方法引用的方式遍历集合元素
        is.forEach(System.out::println);




    }
    //根据Predicate的条件筛选元素
    public static int calAll(Collection books, Predicate predicate){
        int total = 0;
        for (Object obj:books)
        {
            if(predicate.test(obj)){
                total++;
            }
        }
        return total;
    }

}
