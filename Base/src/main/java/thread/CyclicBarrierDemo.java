package thread;

import java.util.concurrent.CyclicBarrier;

/**
 * @Description:CyclicBarrier
 * @Author:BigRedCaps
 */
public class CyclicBarrierDemo extends Thread
{
    @Override
    public void run ()
    {
        System.out.println("开始进行数据汇总和分析");
    }

    /**
     * 注意：
     * 1.如果设定了parties的个数，但是因为某种原因导致没有足够多的线程来调用await,这时会导致所有的线程都被阻塞；
     * 2.为了避免这种问题，可以通过调用await(timeout,unit)方法设定超时等待时间，即使没有足够多的线程来去完成，也会解除
     * 阻塞状态，也会被唤醒；
     * 3.可以通过reset方法重置计数器,会使得进入到await方法的线程触发一个异常BrokenBarrierException,来进行相应的处理；
     *
     */
    public static void main (String[] args)
    {
        // parties=3代表当前的参与者有三个，
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3,new CyclicBarrierDemo());

        new DataImportThread("path1",cyclicBarrier).start();
        new DataImportThread("path2",cyclicBarrier).start();
        new DataImportThread("path3",cyclicBarrier).start();
        cyclicBarrier.reset();

        // TODO 希望三个线程执行结束后，再做一个汇总处理
    }
}
