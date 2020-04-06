package many2many;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import bean.Course;
import bean.Stu;
import util.MyBatisSqlSessionFactory;

public class Many2ManyTest {
	@Test
	public void saveStu(){
		SqlSession session = MyBatisSqlSessionFactory.openSession(true);
		Many2ManyMapper mmm = session.getMapper(Many2ManyMapper.class);
		Stu stu1 = new Stu("rose");
		Stu stu2 = new Stu("jake");
		Stu stu3 = new Stu("tom");
		mmm.saveStu(stu1);
		mmm.saveStu(stu2);
		mmm.saveStu(stu3);
		session.close();
	}
	
	@Test
	public void saveCourse(){
		SqlSession session = MyBatisSqlSessionFactory.openSession(true);
		Many2ManyMapper mmm = session.getMapper(Many2ManyMapper.class);
		Course course1 = new Course("java");
		Course course2 = new Course("oracle");
		mmm.saveCourse(course1);
		mmm.saveCourse(course2);
		session.close();
	}
	
	@Test
	public void selectStuById(){
		SqlSession session = MyBatisSqlSessionFactory.openSession(true);
		Many2ManyMapper mmm = session.getMapper(Many2ManyMapper.class);
		Stu stu = mmm.findStuById(1);
		System.out.println(stu);
		session.close();
	}
	
	@Test
	public void selectCourseById(){
		SqlSession session = MyBatisSqlSessionFactory.openSession(true);
		Many2ManyMapper mmm = session.getMapper(Many2ManyMapper.class);
		Course course = mmm.findCourseById(2);
		System.out.println(course);
		session.close();
	}
	
	@Test
	public void saveStu_Course(){
		SqlSession session = MyBatisSqlSessionFactory.openSession(true);
		Many2ManyMapper mmm = session.getMapper(Many2ManyMapper.class);
		Stu stu = mmm.findStuById(1);
		Course course0 = mmm.findCourseById(1);
		Course course1 = mmm.findCourseById(2);
		mmm.saveStu_Course(stu, course0);
		mmm.saveStu_Course(stu, course1);
		session.close();
	}
	
	@Test
	public void selectStuAndCourse(){
		SqlSession session = MyBatisSqlSessionFactory.openSession(true);
		Many2ManyMapper mmm = session.getMapper(Many2ManyMapper.class);
		Stu stu = mmm.findStuAndCourse(1);
		System.out.println(stu);
		session.close();
	}
	
	@Test
	public void selectCourseAndStu(){
		SqlSession session = MyBatisSqlSessionFactory.openSession(true);
		Many2ManyMapper mmm = session.getMapper(Many2ManyMapper.class);
		Course course = mmm.findCourseAndStu(1);
		System.out.println(course);
		session.close();
	}
	

}
