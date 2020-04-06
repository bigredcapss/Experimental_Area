package bean;

import java.util.Set;

public class Course {
	private int id;
	private String name;
	private Set<Stu> stus;
	
	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", stus=" + stus + "]";
	}
	public Course() {
	}
	public Course(String name) {
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
	public Set<Stu> getStus() {
		return stus;
	}
	public void setStus(Set<Stu> stus) {
		this.stus = stus;
	}
	
	

}
