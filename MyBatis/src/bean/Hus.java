package bean;

public class Hus implements Comparable<Hus>{
	private int id;
	private String name;
	private int age;
	//类与类之间的关联
	private Wife wife;

	
	@Override
	public String toString() {
		return "Hus [id=" + id + ", name=" + name + ", age=" + age + ", wife=" + wife + "]";
	}
	public Wife getWife() {
		return wife;
	}
	public void setWife(Wife wife) {
		this.wife = wife;
	}
	public Hus() {
		super();
	}
	public Hus(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	public Hus(int id, String name, int age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}
	public long getId() {
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
	@Override
	public int compareTo(Hus o) {
		// TODO Auto-generated method stub
		return (int) (this.getId()-o.getId());
	}
	

}
