package aop.proxy.staticproxy;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import pojo.BookService;

public class StaticProxyTest {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext cp = new ClassPathXmlApplicationContext("aop/proxy/staticproxy/staticproxy.xml");
		BookService bs = (BookService) cp.getBean("service");
		bs.saveBook(1, "java");
		bs.get(1);
		bs.list();

	}

}
