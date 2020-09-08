package failfast;

import java.util.ArrayList;
import java.util.List;


/**
 * 两个线程并发操作操作同一个集合
 */

public class FailFastTest {

    private static final List<String> list = new ArrayList<String>();

    public static void main(String[] args) {
        new ThreadAdd(list).start();
        new ThreadIterate(list).start();
    }
}
