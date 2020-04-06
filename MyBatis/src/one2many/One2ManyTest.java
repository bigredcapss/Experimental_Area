package one2many;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import bean.Order;
import bean.User;
import util.MyBatisSqlSessionFactory;

public class One2ManyTest {
	@Test
	public void saveUserAndOrder(){
		SqlSession session = MyBatisSqlSessionFactory.openSession(true);
		One2ManyMapper omm = session.getMapper(One2ManyMapper.class);
		User user = new User("lisi");
		Order order1 = new Order("order1",56);
		order1.setUser(user);
		Order order2 = new Order("order2",96);
		order2.setUser(user);
		Order order3 = new Order("order3",89);
		order3.setUser(user);
		omm.saveUser(user);
		omm.saveOrder(order1);
		omm.saveOrder(order2);
		omm.saveOrder(order3);	
	}
	
	@Test
	public void findUserAndOrder(){
		SqlSession session = MyBatisSqlSessionFactory.openSession(true);
		One2ManyMapper omm = session.getMapper(One2ManyMapper.class);
		User user = omm.findUserAndOrder(1);
		System.out.println(user);
	}
	
	@Test
	public void findUserAndOrder2(){
		SqlSession session = MyBatisSqlSessionFactory.openSession(true);
		One2ManyMapper omm = session.getMapper(One2ManyMapper.class);
		List<User> user = omm.findUserAndOrder2();
		System.out.println(user);
	}

}
