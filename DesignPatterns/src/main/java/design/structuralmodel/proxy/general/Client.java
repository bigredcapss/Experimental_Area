package design.structuralmodel.proxy.general;

/**
 * 代理模式的通用写法测试
 */
public class Client {

    public static void main(String[] args) {

        Proxy proxy = new Proxy(new RealSubject());
        proxy.request();

    }

}
