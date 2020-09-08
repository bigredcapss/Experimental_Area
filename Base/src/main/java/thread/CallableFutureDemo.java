package thread;

import java.util.concurrent.*;

/**
 * @Description:
 * @Author:BigRedCaps
 */
public class CallableFutureDemo implements Callable<String>
{
    @Override
    public String call () throws Exception
    {
        System.out.println("Hello Callable!");
        Thread.sleep(3000);
        return "WE";
    }

    public static void main (String[] args) throws ExecutionException, InterruptedException
    {
        /*CallableFutureDemo callableFutureDemo = new CallableFutureDemo();
        FutureTask futureTask = new FutureTask(callableFutureDemo);
        new Thread(futureTask).start();
        // get()方法是阻塞式方法
        System.out.println(futureTask.get());*/

        // 通过线程池使用Callable与Future
        ExecutorService executorService  = Executors.newFixedThreadPool(1);
        CallableFutureDemo callableFutureDemo = new CallableFutureDemo();
        Future future = executorService.submit(callableFutureDemo);
        System.out.println(future.get());

    }
}
