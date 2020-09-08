package thread;

/**
 * @Description:
 * @Author:BigRedCaps
 */
public class ThreadLocalProblemDemo
{
    private static Integer num = 0;
    public static void main (String[] args)
    {
        Thread[] threads = new Thread[5];
        for (int i = 0; i <5 ; i++)
        {
            threads[i] = new Thread(()->{
               num+=5;
            });
        }
        for (Thread thread:threads)
        {
            thread.start();
        }
    }
}
