package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Description:ConditionDemoNotify
 * @Author:BigRedCaps
 */
public class ConditionDemoNotify extends Thread
{
    private Lock lock;
    private Condition condition;

    public ConditionDemoNotify (Lock lock, Condition condition)
    {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run ()
    {
        System.out.println("begin-ConditionDemoNotify");
        try
        {
            lock.lock();
            condition.signal();
            System.out.println("end-ConditionDemoNotify");
        }finally
        {
            lock.unlock();
        }
    }
}
