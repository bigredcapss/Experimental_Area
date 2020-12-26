package design.creationmode.flyweight.ticket;

/**
 * 客户端查询火车票测试
 * @author BigRedCaps
 * @date 2020/12/26 14:01
 */
public class Client
{
    public static void main (String[] args)
    {
        ITicket ticket = TicketFactory.queryTicket("嘉兴南","上海");
        ticket.showInfo("卧铺");
    }
}
