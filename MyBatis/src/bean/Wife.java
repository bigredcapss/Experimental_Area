package bean;
/**
 * Hus与Wife为一对一的关系，一对一的时候，外键维护在任意一方
 * 数据库表与表之间的关联可以用外键来维护
 * @author WE
 *
 */
public class Wife {
	private int id;
	private String name;
	private int age;
	//类与类之间的关联
	private Hus hus;
	
	@Override
	public String toString() {
		return "Wife [id=" + id + ", name=" + name + ", age=" + age + ", hus=" + hus + "]";
	}

	public Hus getHus() {
		return hus;
	}

	public void setHus(Hus hus) {
		this.hus = hus;
	}

	public Wife() {
		super();
	}
	
	public Wife(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public Wife(int id, String name, int age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	

}
