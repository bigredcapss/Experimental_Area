package one2one;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import org.apache.ibatis.annotations.Param;

import bean.Hus;
import bean.Wife;

public interface One2OneMapper {
	void saveHus (Hus hus);
	/**
	 * 接收多个参数
	 * @param id  @Param("id")对应sql语句中的#{id}，相当于起别名
	 * @param name
	 * @param age
	 */
	void updateHus (@Param("id") int id, @Param("name") String name, @Param("age") int age);

	void deleteHus (int id);
	Hus findHusById (int id);
	List<Hus> findHus ();
	Set<Hus> findHus_Set ();
	SortedSet<Hus> findHus_SortedSet ();
	
	/**
	 * 用map集合去接收，一个map集合对应结果集中的一行数据，结果集的列名为key,列名对应的值为value,所以K为String，V为Object
	 * @return
	 */
	Map<String,Object> findHus_Map (int id);
	//返回多行数据
	List<Map<String,Object>> findHus_Maps ();
	
	int findHus_count ();
	
	List<String> findHus_name ();
	/**
	 * 多表关联存储
	 * @param wife
	 */
	void saveWife (Wife wife);
	
	/**
	 * 查询出来hus的同时，通过Hus可以直接取到wife对象
	 */
	Hus findHus_wife (int id);
	/**
	 * 方法一
	 * @return
	 */
	List<Hus> findHuss_wife ();
	/**
	 * 方法二
	 */
	List<Hus> findHuss_wife1 ();
	/**
	 * 方法三
	 */
	List<Hus> findHuss_wife2 ();
	
	/**
	 * 方法四
	 */
//	Wife selectWifeByHus_Id(int id);
	List<Hus> findHuss_wife3 ();
	
	/**
	 * 方法五
	 */
	List<Hus> findHuss_wife4 ();
	
}
