package aop.proxy.cglibproxy;


import pojo.ProductService;

public class CglibProxyTest {
	public static void main(String[] args) {
		/**
		 * 硬编码方式实现
		 */
		CglibProxy cp =new CglibProxy();
		ProductService ps = (ProductService) cp.getProxy(ProductService.class);
		ps.saveProduct(1, "tom");
		ps.getProduct();
		
		
		
	}

}
