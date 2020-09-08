package failfast;

import java.util.Iterator;
import java.util.List;

/**
 * 迭代元素的线程
 */
public class ThreadIterate extends Thread {

    private List list;

    public ThreadIterate(List list){
        this.list=list;
    }

    @Override
    public void run() {
        while(true) {
            for (Iterator iteratorTmp = list.iterator(); iteratorTmp.hasNext(); ) {
                iteratorTmp.next();
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
