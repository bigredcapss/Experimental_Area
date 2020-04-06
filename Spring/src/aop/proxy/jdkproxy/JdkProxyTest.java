package aop.proxy.jdkproxy;


import pojo.BookService;
import pojo.BookServiceImpl;

import java.lang.reflect.Proxy;

/**
 * JDK代理：
 * 前提：目标对象必须实现所有接口
 * @author WE
 *
 */
public class JdkProxyTest {
	public static void main(String[] args) {
		//第一步：构建目标对象
		BookServiceImpl bs = new BookServiceImpl();
		//第二步：构建处理器
		MyHandler handler = new MyHandler();
		//第三步：给处理器传递目标对象
		handler.setTarget(bs);
		/**
		 * 这里通过硬编码的方法创建代理对象，无法通配，且不够灵活
		 * 作用：这里的代理对象是通过调用jdk中的方法创建的
		 * Proxy.newProxyInstance(arg0, arg1, arg2)
		 * 第一个参数：表示类加载器
		 * 第二个参数：表示目标对象实现的的所有接口Class[] arg1
		 * 第三个参数：表示处理器(对切面所表示的所有方法生效)
		 * 对于第三个参数，两种方法获取：1.采用匿名内部类构建一个处理器；2.单独封装一个处理器
		 */
		BookService proxy = (BookService) Proxy.newProxyInstance(bs.getClass().getClassLoader(), bs.getClass().getInterfaces(), handler);
		proxy.saveBook(1, "tom");
		proxy.get(1);
		proxy.list();
		
	}

}
