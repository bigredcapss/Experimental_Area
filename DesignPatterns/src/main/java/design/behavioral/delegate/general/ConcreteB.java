package design.behavioral.delegate.general;

/**
 * 具体执行角色B
 * @author BigRedCaps
 * @date 2020/12/27 11:10
 */
public class ConcreteB implements ITask
{
    @Override
    public void doTask ()
    {
        System.out.println("任务执行角色，B执行");
    }
}
