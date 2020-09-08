package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Description:ConditionDemoWait
 * @Author:BigRedCaps
 */
public class ConditionDemoWait extends Thread
{
    private Lock lock;
    private Condition condition;

    public ConditionDemoWait (Lock lock, Condition condition)
    {
        this.lock = lock;
        this.condition = condition;
    }

    /**
     * 之前的做法是
     * synchronized(class){
     *     class.await();
     * }
     */
    @Override
    public void run ()
    {
        System.out.println("begin-ConditionDemoWait");
        try
        {
            lock.lock();
            condition.await();// await()一定要在锁的范围内，否则报错
            System.out.println("end-ConditionDemoWait");
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }finally
        {
            lock.unlock();
        }
    }
}
