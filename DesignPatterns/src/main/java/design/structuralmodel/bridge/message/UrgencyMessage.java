package design.structuralmodel.bridge.message;

/**
 * 加急消息----AbstractMessage增强类（修正抽象消息角色）
 * @author BigRedCaps
 * @date 2020/12/28 13:10
 */
public class UrgencyMessage extends AbstractMessage
{
    public UrgencyMessage (IMessage iMessage)
    {
        super(iMessage);
    }

    // 增强发送消息方法
    @Override
    void sendMessage (String message, String toUser)
    {
        message = "【加急】"+message;
        super.sendMessage(message, toUser);
    }

    public Object watch(String messageId){
        return null;
    }
}
