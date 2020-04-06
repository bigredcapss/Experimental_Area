package bean;

import java.util.Set;
/**
 * 在数据库中维护主外键关系；实体类中也维护实体关系，两者不一样
 * @author WE
 *
 */
public class User {
	private int id;
	private String name;
	//维护实体关系：一个用户多个订单
	private Set<Order> orders;
	
	public User(String name) {
		super();
		this.name = name;
	}
	public User() {
		super();
	}
	public User(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Order> getOrders() {
		return orders;
	}
	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", orders=" + orders + "]";
	}
	
	
	

}
