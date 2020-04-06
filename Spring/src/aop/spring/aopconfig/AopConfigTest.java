package aop.spring.aopconfig;


import org.springframework.context.support.ClassPathXmlApplicationContext;


import pojo.BookService;
import pojo.BookServiceImpl;
import pojo.ProductService;

public class AopConfigTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClassPathXmlApplicationContext cp = new ClassPathXmlApplicationContext("aop/spring/aopconfig/aopconfig.xml");
		BookService bs = cp.getBean("target", BookService.class);
		bs.saveBook(1, "wnaeneen");
		
		ProductService ps = cp.getBean("target1", ProductService.class);
		ps.getProduct();

	}

}
