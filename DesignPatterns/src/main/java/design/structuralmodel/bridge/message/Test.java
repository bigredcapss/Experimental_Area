package design.structuralmodel.bridge.message;

/**
 * 桥接模式测试类--发送消息Demo
 * @author BigRedCaps
 * @date 2020/12/28 13:18
 */
public class Test
{
    public static void main (String[] args)
    {
        IMessage message = new SmsMessage();
        AbstractMessage abastractMessage = new NormalMessage(message);
        abastractMessage.sendMessage("加班申请","王总");

        message = new EmailMessage();
        abastractMessage = new UrgencyMessage(message);
        abastractMessage.sendMessage("加班申请","王总");
    }
}
