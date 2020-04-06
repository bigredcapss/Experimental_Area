package aop.spring.autoadvisor;


import org.springframework.context.support.ClassPathXmlApplicationContext;

import pojo.BookService;
import pojo.BookServiceImpl;
import pojo.ProductService;

public class AutoAdvisorTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClassPathXmlApplicationContext cp = new ClassPathXmlApplicationContext("aop/spring/autoadvisor/autoadvisor.xml");
		BookService bs = cp.getBean("target1", BookService.class);
		bs.saveBook(1, "yyy");
		
		ProductService ps = cp.getBean("target2", ProductService.class);
		ps.getProduct();

	}

}
