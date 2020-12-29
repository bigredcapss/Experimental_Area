package design.structuralmodel.bridge.message;

/**
 * 邮件消息类型----具体的消息实现角色
 * @author BigRedCaps
 * @date 2020/12/28 13:15
 */
public class EmailMessage implements IMessage
{
    @Override
    public void send (String message, String toUser)
    {
        System.out.println("使用邮件消息发送" + message + "给" + toUser);
    }
}
