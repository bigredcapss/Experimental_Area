package thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:J.U.C保证线程安全
 * @Author:BigRedCaps
 */
public class LockDemo
{
    static Lock lock = new ReentrantLock();
    public static int count=0;
    public static void incr(){
        try {
            lock.lock();
            Thread.sleep(1);
            count++; //count++ (只会由一个线程来执行)
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally
        {
            lock.unlock();
        }

    }
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            new Thread(LockDemo::incr).start();
        }
        Thread.sleep(4000);
        System.out.println("result:"+count);

    }
}
