package aop.spring.after;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import pojo.BookService;

public class AfterTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClassPathXmlApplicationContext cp = new ClassPathXmlApplicationContext("aop/spring/after/after.xml");
		BookService bs = (BookService) cp.getBean("proxy");
		bs.saveBook(1, "jjjj");
		

	}

}
