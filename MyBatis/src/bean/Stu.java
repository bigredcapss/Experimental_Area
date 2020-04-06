package bean;

import java.util.Set;

public class Stu {
	private int id;
	private String name;
	//多对多，维护实体关系
	private Set<Course> courses;
	@Override
	public String toString() {
		return "Stu [id=" + id + ", name=" + name + ", courses=" + courses + "]";
	}
	public Stu() {
	}
	public Stu(String name) {
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
	public Set<Course> getCourses() {
		return courses;
	}
	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
	

}
