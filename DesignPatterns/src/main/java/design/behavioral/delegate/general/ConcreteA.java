package design.behavioral.delegate.general;

/**
 * 具体任务执行角色A
 * @author BigRedCaps
 * @date 2020/12/27 11:08
 */
public class ConcreteA implements ITask
{
    @Override
    public void doTask ()
    {
        System.out.println("任务执行角色，A执行");
    }
}
