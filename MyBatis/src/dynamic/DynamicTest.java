package dynamic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import bean.Student;
import util.MyBatisSqlSessionFactory;

public class DynamicTest {
	@Test
	public void selectStudent_if(){
		SqlSession session = MyBatisSqlSessionFactory.openSession(true);
		DynamicMapper dm = session.getMapper(DynamicMapper.class);
		List<Student> student = dm.findStudent_if(0, null, null);
		for(Student stu:student){
			System.out.println(stu);
			System.out.println(stu.getPhone());
		}
	}
	
	@Test
	public void selectStudent_ifs(){
		SqlSession session = MyBatisSqlSessionFactory.openSession(true);
		DynamicMapper dm = session.getMapper(DynamicMapper.class);
		Map<String, Object> map = new HashMap<>();
		map.put("stud_Id", 2);
		map.put("name", "tt");
		map.put("email", "123@163.com");
		List<Student> student = dm.findStudent_ifs(map);
		System.out.println(student);
	}
	
	@Test
	public void selectStudent_where(){
		SqlSession session = MyBatisSqlSessionFactory.openSession(true);
		DynamicMapper dm = session.getMapper(DynamicMapper.class);
		Map<String, Object> map = new HashMap<>();
//		map.put("stud_Id", 2);
//		map.put("name", "tt");
//		map.put("email", "123@163.com");
		List<Student> student = dm.findStudent_where(map);
		System.out.println(student);
	}
	
	@Test
	public void selectStudent_choose(){
		SqlSession session = MyBatisSqlSessionFactory.openSession(true);
		DynamicMapper dm = session.getMapper(DynamicMapper.class);
		Map<String, Object> map = new HashMap<>();
//		map.put("stud_Id", 2);
//		map.put("name", "tt");
//		map.put("email", "123@163.com");
		List<Student> student = dm.findStudent_choose(map);
		System.out.println(student);
	}
	
	@Test
	public void studentUpdate(){
		SqlSession session = MyBatisSqlSessionFactory.openSession(true);
		DynamicMapper dm = session.getMapper(DynamicMapper.class);
		Map<String, Object> map = new HashMap<>();
		map.put("name", "kkll");
		map.put("id",2);
		dm.studentUpdate(map);
	}


}
