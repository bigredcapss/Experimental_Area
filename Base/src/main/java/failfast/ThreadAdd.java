package failfast;

import java.util.List;

/**
 * 添加元素的线程
 */
public class ThreadAdd extends Thread {

    private List list;

    public ThreadAdd(List list) {
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("loop execute :"+i);
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add(i);
        }
    }
}
