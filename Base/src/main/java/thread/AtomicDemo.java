package thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 * @Author:BigRedCaps
 */
public class AtomicDemo {

    //    public static int count=0;
    private static AtomicInteger atomicInteger=new AtomicInteger(0);
    public static void incr(){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        count++; //count++ (只会由一个线程来执行)
        atomicInteger.incrementAndGet();
    }
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            new Thread(AtomicDemo::incr).start();
        }
        Thread.sleep(4000);
        System.out.println("result:"+atomicInteger.get());
    }
}
