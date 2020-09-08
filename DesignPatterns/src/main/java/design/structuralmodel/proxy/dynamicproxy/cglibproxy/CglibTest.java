package design.structuralmodel.proxy.dynamicproxy.cglibproxy;

import net.sf.cglib.core.DebuggingClassWriter;

/**
 * Cglib代理测试
 */
public class CglibTest {
    public static void main(String[] args) {

        try {


            //JDK是采用实现接口的方式，要求代理的目标对象一定要实现接口
            //CGLib采用继承的方式，覆盖父类方法
            //目的：都是通过生成字节码，生成一个新的类，去实现增强代码逻辑的功能

            //JDK Proxy 对于用户而言，必须要有一个接口实现，依赖性更强，调用也更复杂
            //CGLib 可以代理任意一个普通的类，没有任何要求

            //CGLib 生成代理逻辑更复杂，效率,调用效率更高，生成一个包含了所有的逻辑的FastClass，不再需要反射调用
            //JDK Proxy生成代理的逻辑简单，执行效率相对要低，每次都要反射动态调用

            //CGLib 有个坑，CGLib不能代理final的方法


            //将Cglib代理生成的类的字节码文件输出到指定目录
            System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,"D://cglib_proxy_classes");

            Customer obj = (Customer) new CGlibMeipo().getInstance(Customer.class);
            System.out.println(obj);
            obj.findLove();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
