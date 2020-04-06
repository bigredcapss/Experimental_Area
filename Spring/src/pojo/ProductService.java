package pojo;

import org.springframework.stereotype.Component;

/**
 * 给没有接口的类构建代理对象
 * @author WE
 *
 */
@Component("target2")
public class ProductService {
	public void saveProduct(int id,String name){
		System.out.println("saveProduct......");
	}
	public int getProduct(){
		System.out.println("getProduct()........");
		return 23;
	}

}
