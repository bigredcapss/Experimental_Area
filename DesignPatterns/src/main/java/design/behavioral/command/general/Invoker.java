package design.behavioral.command.general;

/**
 * 请求者角色---接收客户端的命令，并执行命令
 * @author BigRedCaps
 * @date 2021/1/3 16:17
 */
public class Invoker
{
    private ICommand iCommand;
    public Invoker(ICommand iCommand){
        this.iCommand = iCommand;
    }
    public void action(){
        this.iCommand.execute();
    }
}
