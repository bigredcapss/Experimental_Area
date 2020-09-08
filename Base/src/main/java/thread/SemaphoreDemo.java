package thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Description:Semaphore
 * @Author:BigRedCaps
 */
public class SemaphoreDemo
{
    public static void main (String[] args)
    {
        // 当前可以获得的最大许可数量是5个
        Semaphore semaphoreDemo = new Semaphore(5);

        for (int i = 0; i < 10; i++)
        {
            new Car(i,semaphoreDemo).start();
        }
    }

    static class Car extends Thread{
        private int num;
        private Semaphore semaphore;
        public Car(int num,Semaphore semaphore){
            this.num = num;
            this.semaphore = semaphore;
        }

        @Override
        public void run ()
        {
            try
            {
                semaphore.acquire();// 获得许可，若获取不到许可，就会被阻塞
                System.out.println("第"+num+"辆车占用了一个停车位");
                TimeUnit.SECONDS.sleep(2000);
                System.out.println("第"+num+"辆车走了");
                semaphore.release();// 释放许可
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
