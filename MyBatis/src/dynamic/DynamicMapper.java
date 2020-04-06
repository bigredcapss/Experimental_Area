package dynamic;

import java.util.List;
import java.util.Map;

import bean.Student;

/**
 * 动态sql语句
 * @author WE
 *
 */
public interface DynamicMapper {
	List<Student> findStudent_if (int studId, String name, String email);
	/**
	 * 传多个参数时，一般用map集合封装
	 * @param map
	 * @return
	 */
	List<Student> findStudent_ifs (Map<String, Object> map);
	
	List<Student> findStudent_where (Map<String, Object> map);
	
	List<Student> findStudent_choose (Map<String, Object> map);
	
	/**
	 * 动态sql语句的更新操作
	 */
	void studentUpdate (Map<String, Object> map);

	
	

	

}
