package design.creationmode.flyweight.ticket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 火车票工厂类
 * 思考：问题：当客户端进行查询时，系统通过TicketFactory直接创建一个火车票对象，但是这样做的话，当某个瞬间如果有大量的用户
 * 请求同一张票的信息时，系统就会创建出大量该火车票对象，系统内存压力骤增？那么怎么办呢？
 *
 * 解决办法：缓存该票对象，然后复用提供给其它查询请求，这样一个对象就足以支撑数以千计的查询请求，对内存完全无压力。因此
 * 使用享元模式可以解决该问题。优化后的代码，如方法2所示
 *
 * @author BigRedCaps
 * @date 2020/12/26 13:45
 */
public class TicketFactory
{
    /**
     * 方法1
     * @param from
     * @param to
     * @return
     */
    /*public static ITicket queryTicket(String from,String to){
        return new TrainTicket(from,to);
    }*/

    private static Map<String,ITicket> sTicketPool = new ConcurrentHashMap<>();
    /**
     * 方法2
     * @param from
     * @param to
     * @return
     */

    public static ITicket queryTicket(String from,String to){
        String key = from + "->" + to;
        if(TicketFactory.sTicketPool.containsKey(key)){
            System.out.println("使用缓存："+key);
            return TicketFactory.sTicketPool.get(key);
        }
        System.out.println("首次查询，创建对象："+key);
        ITicket ticket = new TrainTicket(from,to);
        TicketFactory.sTicketPool.put(key,ticket);
        return ticket;
    }



}
