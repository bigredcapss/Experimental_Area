package bean;

public class User {
	@Override
	public String toString() {
		return "User [name=" + name + ", password=" + password + "]";
	}
	private String name;
	private String password;
	//地区
	private Address addre;
	public User() {
	}
	public User(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Address getAddre() {
		return addre;
	}
	public void setAddre(Address addre) {
		this.addre = addre;
	}
	
	

}
