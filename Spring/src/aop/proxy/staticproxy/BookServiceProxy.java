package aop.proxy.staticproxy;

import pojo.BookService;
import pojo.BookServiceImpl;
/**
 * 代理对象
 * @author WE
 *
 */
public class BookServiceProxy implements BookService{
	
	private BookServiceImpl bs;

	public BookServiceImpl getBs() {
		return bs;
	}

	public void setBs(BookServiceImpl bs) {
		this.bs = bs;
	}

	@Override
	public void saveBook(int id, String name) {
		// TODO Auto-generated method stub
		System.out.println("before..");
		bs.saveBook(id,name);
		System.out.println("after..");
		
	}

	@Override
	public String get(int id) {
		// TODO Auto-generated method stub
		System.out.println("before..");
		//目标对象的方法有返回值，因此这里也需要返回值
		String str = bs.get(id);
		System.out.println("after..");
		return null;
	}

	@Override
	public void list() {
		// TODO Auto-generated method stub
		System.out.println("before..");
		bs.list();
		System.out.println("after..");
		
	}

}
