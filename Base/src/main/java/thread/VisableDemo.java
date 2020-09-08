package thread;

/**
 * @Description:多线程可见性问题Demo
 * @Author:BigRedCaps
 */
public class VisableDemo {

    //volatile可以解决可见性、[有序性]
    public volatile static boolean stop=false;

    public static void main(String[] args) throws InterruptedException {
        Thread thread=new Thread(()->{
            int i=0;
            while(!stop){
                i++;
            }
            System.out.println("result:"+i);
        });
        thread.start();
        System.out.println("begin start thread");
        Thread.sleep(1000);
        stop=true; //主线程中修改stop的值
    }
}
