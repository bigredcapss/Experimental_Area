package bean;

public class Student {
	private int id;
	private String name;
	private int age;
	private String email;
	//对象初始化时执行的方法
	public void stu_init(){
		System.out.println("student-----intit");
	}
	//对象销毁时执行的方法
	public void destroy(){
		System.out.println("studnet......destroy");
	}
	
	
	public Student() {
	}
	public Student(int id, String name, int age, String email) {
		System.out.println("$$$$$$$$$$$$$");//测试spring是否通过构造器构造对象
		this.id = id;
		this.name = name;
		this.age = age;
		this.email = email;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age + ", email=" + email + "]";
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
