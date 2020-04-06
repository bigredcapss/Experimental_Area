package one2many;

import java.util.List;
import java.util.Set;

import bean.Order;
import bean.User;

public interface One2ManyMapper {
	void saveUser (User user);
	void saveOrder (Order order);
	/**
	 * 基于用户的id查询出用户的信息(级联的查询出所有的订单)
	 * @param id
	 * @return
	 */
	User findUserAndOrder (int id);
	/**
	 * 基于用户的id查询所有的订单
	 * @param id
	 * @return
	 */
	Set<Order> findOrderByUser_id (int id);
	/**
	 * 查询所有的用户及订单
	 * @return
	 */
	List<User> findUserAndOrder2 ();
	
	

}
