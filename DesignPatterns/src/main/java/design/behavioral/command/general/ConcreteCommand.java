package design.behavioral.command.general;

/**
 * 具体命令角色----该类内部维护一个接收者（Receiver),在其execute()方法中调用Receiver的相关方法
 * @author BigRedCaps
 * @date 2021/1/3 16:20
 */
public class ConcreteCommand implements ICommand
{
    private Receiver receiver = new Receiver();

    @Override
    public void execute ()
    {
        this.receiver.action();
    }
}
