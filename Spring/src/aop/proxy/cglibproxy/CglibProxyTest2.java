package aop.proxy.cglibproxy;

import pojo.ProductService;

public class CglibProxyTest2 {

	public static void main(String[] args) {
		ProductService target = new ProductService();
		CglibProxy2 cp2 = new CglibProxy2();
		cp2.setTarget(target);
		ProductService ps1 = (ProductService) cp2.getProxy();
		ps1.saveProduct(1, "tom");
		ps1.getProduct();
		
		/**
		 * 交给spring管理
		 */
/*		ClassPathXmlApplicationContext cp = new ClassPathXmlApplicationContext("aop/proxy/cglibproxy/cglib.xml");
		ProductService ps = (ProductService) cp.getBean("factory");
		ps.saveProduct(1, "jake");*/

	}

}
