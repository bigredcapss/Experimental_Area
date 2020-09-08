package thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Description:数据导入线程
 * @Author:BigRedCaps
 */
public class DataImportThread extends Thread
{
    private String path;
    private CyclicBarrier cyclicBarrier;

    public DataImportThread (String path, CyclicBarrier cyclicBarrier)
    {
        this.path = path;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run ()
    {
        System.out.println("开始导入"+path+"位置的数据");
        try
        {
            cyclicBarrier.await();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        } catch (BrokenBarrierException e)
        {
            e.printStackTrace();
        }
    }
}
