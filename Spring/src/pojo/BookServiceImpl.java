package pojo;

import org.springframework.stereotype.Component;

/**
 * 给有接口的类构建代理对象
 * 目标对象
 * @author WE
 *
 */
@Component("target1")
public class BookServiceImpl implements BookService{

	@Override
	public void saveBook(int id, String name) {
		// TODO Auto-generated method stub
		System.out.println("saveBook...");
		//int a =10/0;
	}

	@Override
	public String get(int id) {
		// TODO Auto-generated method stub
		System.out.println("get....");
		return "test...ok";
	}

	@Override
	public void list() {
		// TODO Auto-generated method stub
		System.out.println("list....");;
		
	}

}
