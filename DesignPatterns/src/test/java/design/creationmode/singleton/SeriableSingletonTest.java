package design.creationmode.singleton;

import design.creationmode.singleton.seriable.SeriableSingleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 序列化破坏单例模式测试
 *
 * 解决办法：在SeriableSingleton.java中增加readResolve()方法，可参考SeriableSingleton.java的readResolve()方法；
 *
 * 至于为什么增加了readResolve()方法就解决了序列化破坏单例的问题，可参考readObject()方法（分配内存）的源码
 * readObject()-->readObject0()-->checkResolve(readOrdinaryObject(unshared))-->readOrdinaryObject()-->
 * hasReadResolveMethod(),invokeReadResolve(obj)....
 *
 * 总之阅读源码的这一系列过程就是判断你是否写了readResolve()方法，写了就不让新建对象，返回单例对象；没写就利用反射新建一
 * 个对象
 *
 */
public class SeriableSingletonTest {
    public static void main(String[] args) {

        SeriableSingleton s1 = null;
        SeriableSingleton s2 = SeriableSingleton.getInstance();

        FileOutputStream fos = null;
        try {

            //把s2这个单例对象写到磁盘上去
            fos = new FileOutputStream("SeriableSingleton.obj");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(s2);
            oos.flush();
            oos.close();

            //再把磁盘上的单例对象读入到内存中，并赋值给s1
            FileInputStream fis = new FileInputStream("SeriableSingleton.obj");
            ObjectInputStream ois = new ObjectInputStream(fis);
            s1 = (SeriableSingleton)ois.readObject();
            ois.close();

            System.out.println(s1);
            System.out.println(s2);
            //若s1和s2相等，则说明在序列化前后，s1,s2自始至终始终是一个对象，若不相等，则说明破坏了单例模式
            System.out.println(s1 == s2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
