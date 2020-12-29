package design.structuralmodel.bridge.message;

/**
 * 短信消息类型----具体的消息实现角色
 * @author BigRedCaps
 * @date 2020/12/28 13:16
 */
public class SmsMessage implements IMessage
{
    @Override
    public void send (String message, String toUser)
    {
        System.out.println("使用短信消息发送" + message + "给" + toUser);
    }
}
