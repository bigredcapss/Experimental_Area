package thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * @Description:Fork/Join的使用
 * @Author:BigRedCaps
 */
public class ForkJoinDemo extends RecursiveTask<Integer>
{
    // 计算1-10的总和
    // 分割任务的阈值：例如每个任务只能进行2个数字相加，那么阈值就是2
    private final int THREHOLD = 2;// 设置阈值为2
    private int start;
    private int end;

    public ForkJoinDemo (int start, int end)
    {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute ()
    {
        int sum = 0;
        boolean canCompute = (end-start)<=THREHOLD;
        if(canCompute)//任务不能再分割
        {
            // 打印任务拆分的结果
            System.out.println("start:"+start+"-end"+end);
            for (int i = start; i <=end ; i++)
            {
                sum+=i;
            }
        }else{
            int middle = (start+end)/2;
            ForkJoinDemo left = new ForkJoinDemo(start,middle);
            ForkJoinDemo right = new ForkJoinDemo(middle+1,end);
            left.fork();
            right.fork();
            int leftResult = left.join();
            int rightResult = right.join();
            sum =leftResult+rightResult;
        }
        return sum;
    }

    public static void main (String[] args) throws ExecutionException, InterruptedException
    {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinDemo forkJoinDemo = new ForkJoinDemo(1,10);
        Future<Integer> result = forkJoinPool.submit(forkJoinDemo);
        System.out.println(result.get());

    }
}
