package aop.spring.before;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import pojo.BookService;
import pojo.ProductService;

public class BeforeTest {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext cp = new ClassPathXmlApplicationContext("aop/spring/before/before.xml");
		BookService bs = (BookService) cp.getBean("proxy");
		bs.saveBook(1, "tom");
		
		ProductService ps = (ProductService) cp.getBean("proxy1");
		ps.getProduct();
		
				
	}

}
