package bean;

public class Order {
	private int id;
	private String name;
	private double price;
	//维护实体关系：一个订单对应一个用户
	private User user;
	
	@Override
	public String toString() {
		return "Order [id=" + id + ", name=" + name + ", price=" + price + ", user=" + user + "]";
	}
	public Order() {
		super();
	}
	public Order(String name, double price) {
		super();
		this.name = name;
		this.price = price;
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	

}
