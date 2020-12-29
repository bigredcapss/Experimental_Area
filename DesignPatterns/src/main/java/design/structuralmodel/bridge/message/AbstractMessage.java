package design.structuralmodel.bridge.message;

/**
 * 抽象消息角色
 * @author BigRedCaps
 * @date 2020/12/28 13:05
 */
public abstract class AbstractMessage
{
    protected IMessage iMessage;

    public AbstractMessage(IMessage iMessage){
        this.iMessage = iMessage;
    }

    void sendMessage(String message,String toUser){
        this.iMessage.send(message,toUser);
    }

}
