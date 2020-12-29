package design.structuralmodel.bridge.message;

/**
 * 正常的消息----修正抽象消息角色
 * @author BigRedCaps
 * @date 2020/12/28 13:10
 */
public class NormalMessage extends AbstractMessage
{
    public NormalMessage (IMessage iMessage)
    {
        super(iMessage);
    }
}
