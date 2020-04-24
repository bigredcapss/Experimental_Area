package design.structuralmodel.proxy.dynamicproxy.jdkproxy;


import sun.misc.ProxyGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * 这里通过动态代理获得的对象是通过字节码增强技术，从内存中拿到的一个新对象，在一个继承体系内
 *
 * 这里IPerson zhangsan = jdkMeipo.getInstance(new Zhangsan());
 * zhangsan={$Proxy0@573}这个对象存在于内存中--->我们可以把它拿出来
 * 通过把它的字节码输出到.class文件--->在反编译看它的源代码是什么样的
 *
 * 然后，我也可以自己模拟这样的一个生成动态代理对象的过程
 */
public class Test {
    public static void main(String[] args) {
        JdkMeipo jdkMeipo = new JdkMeipo();
        //这里的"zhangsan" 和new Zhangsan()不是同一个对象，只是在同一个继承体系内；
        IPerson zhangsan = jdkMeipo.getInstance(new Zhangsan());
        zhangsan.findLove();


        try
        {
            byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy0",new Class[]{IPerson.class});
            FileOutputStream os = new FileOutputStream("D://$Proxy0.class");

            os.write(bytes);
            os.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        //这里同上
        IPerson zhaoliu = jdkMeipo.getInstance(new ZhaoLiu());
        zhaoliu.findLove();

    }
}
