package bean;

public class UserService {
	private int id;
	private String name;
	private UserDao dao;

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

	public UserService() {
		System.out.println("-----");
	}

	public UserService(UserDao dao) {
		this.dao = dao;
	}

	public UserDao getDao() {
		return dao;
	}

	public void setDao(UserDao dao) {
		this.dao = dao;
	}

	@Override
	public String toString() {
		return "UserService [id=" + id + ", name=" + name + ", dao=" + dao + "]";
	}
	

}
