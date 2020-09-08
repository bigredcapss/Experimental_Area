package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:线程池Demo
 * @Author:BigRedCaps
 */
public class ThreadPoolDemo implements Runnable
{
    public static void main (String[] args)
    {
        // 创建线程数可伸缩的线程池
       ExecutorService executorService =  Executors.newFixedThreadPool(10);

        for (int i = 0; i < 100; i++)
        {
            executorService.execute(new ThreadPoolDemo());
        }
        // 关闭线程池
        executorService.shutdown();
    }

    @Override
    public void run ()
    {
        try
        {
            Thread.sleep(10);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
    }
}
