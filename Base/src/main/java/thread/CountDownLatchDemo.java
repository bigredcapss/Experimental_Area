package thread;

import java.util.concurrent.CountDownLatch;

/**
 * @Description:CountDownLatch
 * @Author:BigRedCaps
 */
public class CountDownLatchDemo
{
    public static void main (String[] args) throws InterruptedException
    {
        // 3实际存储在AQS中的state变量中
        CountDownLatch countDownLatch = new CountDownLatch(3);

        new Thread(()->{
            countDownLatch.countDown();// 倒计时3-1
        }).start();

        new Thread(()->{
            countDownLatch.countDown();// 倒计时2-1
        }).start();

        new Thread(()->{
            countDownLatch.countDown();// 倒计时1-1=0-->触发主线程的唤醒操作
        }).start();

        countDownLatch.await();// 阻塞主线程
        System.out.println("线程执行完毕");

    }
}
