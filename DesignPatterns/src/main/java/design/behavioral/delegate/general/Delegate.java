package design.behavioral.delegate.general;

import java.util.Random;

/**
 * 任务分配/代理角色
 * @author BigRedCaps
 * @date 2020/12/27 11:11
 */
public class Delegate implements ITask
{
    @Override
    public void doTask ()
    {
        System.out.println("代理执行开始...");

        if(new Random().nextBoolean()){
            ITask concreteA = new ConcreteA();
            concreteA.doTask();
        }else {
            ITask concreteB = new ConcreteB();
            concreteB.doTask();
        }

        System.out.println("代理执行结束...");

    }
}
