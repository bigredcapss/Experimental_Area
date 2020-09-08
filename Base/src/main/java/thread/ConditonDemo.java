package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:ConditionDemo
 * @Author:BigRedCaps
 */
public class ConditonDemo
{
    public static void main (String[] args)
    {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        // 两个线程必须要持有同一把锁，在同一条件下，否则无法达到预期的输出效果
        ConditionDemoWait conditionDemoWait = new ConditionDemoWait(lock,condition);
        ConditionDemoNotify conditionDemoNotify = new ConditionDemoNotify(lock,condition);
        conditionDemoWait.start();
        conditionDemoNotify.start();

    }
}
