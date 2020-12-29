package design.structuralmodel.bridge.message;

/**
 * 消息实现角色
 * @author BigRedCaps
 * @date 2020/12/28 13:06
 */
public interface IMessage
{
    /**
     * 发送消息
     * @param message 发送消息的内容
     * @param toUser 接收人
     */
    void send(String message,String toUser);
}
