package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Description:自定义线程池工厂类
 * @Author:BigRedCaps
 */
public class ExecutorsSelf
{
    public static ExecutorService newFixedThreadPool(int nThreads) {
        return new ThreadPoolSelf(nThreads, nThreads,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
    }

}
