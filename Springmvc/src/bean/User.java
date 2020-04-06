package bean;

import java.util.Date;

import javax.validation.constraints.Null;

import org.springframework.format.annotation.DateTimeFormat;

public class User {
	private int id;
	@Null(message="这里必须为空")
	private String name;
	private int age;
	//方式一：将字符串转换为时间，自动完成；该注解出现的位置，全局变量或set方法之上
	//@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dob;
	
	
	public void sayHello(){
		System.out.println("byebye"+"........");
	}
	
	public void sayHello(String name){
		System.out.println("byebye"+"........"+name);
	}
	
	public static void sayYou(){
		System.out.println("static.....");
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + ", dob=" + dob + "]";
	}
	public User() {
	}
	public User(int id, String name, int age) {
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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	

}
