package dao;

import bean.PhoneNumber;
import bean.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import util.MyBatisSqlSessionFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * Student测试类
 * @author WE
 *
 */
public class StudentTest {
	
	@Test
	public void select_stuId_test(){
		//基于字节流读取核心配置文件
		InputStream is;
		try {
			is = Resources.getResourceAsStream("mybatis-config.xml");
		    //构建SqlSessionfactory对象
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
			//构建SqlSession对象
			SqlSession session = factory.openSession();
			//构建接口的实现类(jdk代理)
			StudentMapper sm = session.getMapper(StudentMapper.class);
			Student stu = sm.findStudentById(1);
			System.out.println(stu);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test
	public void insert_stu_test(){
		try {
			//基于字节流读取核心配置文件
			InputStream is =  Resources.getResourceAsStream("mybatis-config.xml");
		    //构建SqlSessionfactory对象
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
			//构建SqlSession对象
			SqlSession session = factory.openSession();
			//构建接口的实现类(jdk代理)
			StudentMapper sm = session.getMapper(StudentMapper.class);
			//调用saveStudent方法
			Student stu = new Student(4,"Lom","123@163.com",new Date());
			sm.saveStudent(stu);
			//要求手动提交事务
			session.commit();
		
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void select_stu_test(){
		//基于字节流读取核心配置文件
		InputStream is;
		try {
			is = Resources.getResourceAsStream("mybatis-config.xml");
		    //构建SqlSessionfactory对象
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
			//构建SqlSession对象
			SqlSession session = factory.openSession();
			//构建接口的实现类(jdk代理)
			StudentMapper sm = session.getMapper(StudentMapper.class);
			List<Student> stu = sm.findAllStudent();
			System.out.println(stu);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void delete_stuId_test(){
		//基于字节流读取核心配置文件
		InputStream is;
		try {
			is = Resources.getResourceAsStream("mybatis-config.xml");
		    //构建SqlSessionfactory对象
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
			//构建SqlSession对象
			SqlSession session = factory.openSession();
			//构建接口的实现类(jdk代理)
			StudentMapper sm = session.getMapper(StudentMapper.class);
			sm.deleteStudentById(2);
			session.commit();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void update_stuId_test(){
		//基于字节流读取核心配置文件
		InputStream is;
		try {
			is = Resources.getResourceAsStream("mybatis-config.xml");
		    //构建SqlSessionfactory对象
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
			//构建SqlSession对象
			SqlSession session = factory.openSession();
			//构建接口的实现类(jdk代理)
			StudentMapper sm = session.getMapper(StudentMapper.class);
			Student stu = new Student(1,"jane","jane@163.com",new Date());
			sm.updateStudent(stu);
			session.commit();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void select_stu(){
		SqlSession session = MyBatisSqlSessionFactory.openSession();
		StudentMapper sm = session.getMapper(StudentMapper.class);
	    Student stu = sm.findStudentById(1);
	    System.out.println(stu);
	}
	
	@Test
	public void insert_stu_phone(){
		try{
			SqlSession session = MyBatisSqlSessionFactory.openSession();
			StudentMapper sm = session.getMapper(StudentMapper.class);
		    Student stu = new Student(2,"tt","123@163.com",new Date());
		    PhoneNumber phone = new PhoneNumber("1221","2223","112");
		    //这里将phone对象设置到stu中，在Java类级别上是行的通的，但存入数据库的时候，phone字段的类型是varchar类型的，因此必须提供类型处理器，将对象转换成字符串
		    stu.setPhone(phone);
		    sm.insertStudentPhone(stu);
		    session.commit();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void select_stu_phone(){
		try{
			SqlSession session = MyBatisSqlSessionFactory.openSession(true);
			StudentMapper sm = session.getMapper(StudentMapper.class);
		    Student stu = sm.findStudentPhone(2);
		    System.out.println(stu);
		    System.out.println(stu.getPhone());
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
