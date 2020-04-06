package many2many;

import org.apache.ibatis.annotations.Param;

import bean.Course;
import bean.Stu;

public interface Many2ManyMapper {
	void saveStu (Stu stu);
	void saveCourse (Course course);
	Stu findStuById (int id);
	Course findCourseById (int id);
	
	/**
	 * 学生选课(传入多个参数)
	 */
	void saveStu_Course (@Param("stu") Stu stu, @Param("course") Course course);
	/**
	 * 查询学生信息以及选择的所有课程,以sql语句中查询出来的列，封装成我们关注的对象
	 */
	Stu findStuAndCourse (int id);
	/**
	 * 查询所有的课程以及该课程下的学生信息
	 */
	Course findCourseAndStu (int id);
	

}
