package one2one;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedSet;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import bean.Hus;
import bean.Wife;
import util.MyBatisSqlSessionFactory;

public class One2OneTest {
	@Test
	public void insert_hus(){
		SqlSession session = MyBatisSqlSessionFactory.openSession(true);
		One2OneMapper oom = session.getMapper(One2OneMapper.class);
		for(int i=0;i<10;i++){
			Hus hus = new Hus("kk"+i,33+i);
		/**
		 * 第一种方式可以不构建接口的实现类:第一个参数是该接口中的全限定方法名，第二个参数是该接口中的该方法的参数，有参数，写在第二个位置，没有则忽略
		 */
//		session.insert("one2one.One2OneMapper.saveHus", hus);
		/**
		 * 第二种方式需要构建接口的实现类
		 */
			oom.saveHus(hus);
		
			System.out.println(hus);
		}
	}
	
	@Test
	public void update_hus(){
		SqlSession session = MyBatisSqlSessionFactory.openSession(true);
		/**
		 * 第一种方式可以不构建接口的实现类:第一个参数是该接口中的全限定方法名，第二个参数是该接口中的该方法的参数，有参数，写在第二个位置，没有则忽略;最多接收两个参数
		 */
//		session.insert("one2one.One2OneMapper.saveHus", hus);
		/**
		 * 第二种方式需要构建接口的实现类
		 */
		One2OneMapper oom = session.getMapper(One2OneMapper.class);
		oom.updateHus(2, "rrr",333);
	}

	@Test
	public void delete_hus(){
		SqlSession session = MyBatisSqlSessionFactory.openSession(true);
		/**
		 * 第一种方式可以不构建接口的实现类:第一个参数是该接口中的全限定方法名，第二个参数是该接口中的该方法的参数，有参数，写在第二个位置，没有则忽略;最多接收两个参数
		 */
		session.delete("one2one.One2OneMapper.deleteHus", 2);

	}
	
	@Test
	public void select_husId(){
		try{
		SqlSession session = MyBatisSqlSessionFactory.openSession(true);
		Hus hus = session.selectOne("one2one.One2OneMapper.findHusById", 1);
		System.out.println(hus);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void select_hus(){
		try{
		SqlSession session = MyBatisSqlSessionFactory.openSession(true);
		List<Hus> hus = session.selectList("one2one.One2OneMapper.findHus");
		System.out.println(hus);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void select_husSet(){
		try{
		SqlSession session = MyBatisSqlSessionFactory.openSession(true);
		One2OneMapper oom = session.getMapper(One2OneMapper.class);
		Set<Hus> hus = oom.findHus_Set();
		System.out.println(hus.getClass());
		System.out.println(hus);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void select_husSortedSet(){
		try{
		SqlSession session = MyBatisSqlSessionFactory.openSession(true);
		One2OneMapper oom = session.getMapper(One2OneMapper.class);
		SortedSet<Hus> hus = oom.findHus_SortedSet();
		System.out.println(hus.getClass());
		System.out.println(hus);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void select_husMap(){
		try{
		SqlSession session = MyBatisSqlSessionFactory.openSession(true);
		One2OneMapper oom = session.getMapper(One2OneMapper.class);
		Map<String,Object> map = oom.findHus_Map(3);
		System.out.println(map.getClass());
		for(Entry<String, Object> en:map.entrySet()){
			System.out.println(en.getKey()+"-"+en.getValue());
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void select_husMaps(){
		try{
		SqlSession session = MyBatisSqlSessionFactory.openSession(true);
		One2OneMapper oom = session.getMapper(One2OneMapper.class);
		List<Map<String,Object>> list = oom.findHus_Maps();
		System.out.println(list.getClass());
		for(Map<String,Object> map:list){
			for(Entry<String, Object> en:map.entrySet()){
				System.out.println(en.getKey()+"-"+en.getValue());
			}
			System.out.println("***********");
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void select_husCount(){
		try{
		SqlSession session = MyBatisSqlSessionFactory.openSession(true);
		One2OneMapper oom = session.getMapper(One2OneMapper.class);
		int count = oom.findHus_count();
		System.out.println(count);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void select_husName(){
		try{
		SqlSession session = MyBatisSqlSessionFactory.openSession(true);
		One2OneMapper oom = session.getMapper(One2OneMapper.class);
		List<String> list = oom.findHus_name();
		for(String name:list){
			System.out.println(name);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 一对一关联保存
	 */
	@Test
	public void save_husWife(){
		try{
		SqlSession session = MyBatisSqlSessionFactory.openSession(true);
		One2OneMapper oom = session.getMapper(One2OneMapper.class);

		Hus hus = new Hus("jake",34);
		Wife wife = new Wife("jane",32);
		wife.setHus(hus);
		oom.saveHus(hus);
		oom.saveWife(wife);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 一对一关联查询
	 */
	@Test
	public void select_husWife(){
		try{
		SqlSession session = MyBatisSqlSessionFactory.openSession(true);
		One2OneMapper oom = session.getMapper(One2OneMapper.class);
		Hus hus = oom.findHus_wife(13);
		System.out.println(hus);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 一对一关联查询
	 * 方法一
	 */
	@Test
	public void select_hussWife(){
		try{
		SqlSession session = MyBatisSqlSessionFactory.openSession(true);
		One2OneMapper oom = session.getMapper(One2OneMapper.class);
		List<Hus> list = oom.findHuss_wife();
		for(Hus hus:list){
			System.out.println(hus);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 一对一关联查询
	 * 方法二
	 */
	@Test
	public void select_hussWife1(){
		try{
		SqlSession session = MyBatisSqlSessionFactory.openSession(true);
		One2OneMapper oom = session.getMapper(One2OneMapper.class);
		List<Hus> list = oom.findHuss_wife1();
		for(Hus hus:list){
			System.out.println(hus);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 一对一关联查询
	 * 方法三
	 */
	@Test
	public void select_hussWife2(){
		try{
		SqlSession session = MyBatisSqlSessionFactory.openSession(true);
		One2OneMapper oom = session.getMapper(One2OneMapper.class);
		List<Hus> list = oom.findHuss_wife2();
		for(Hus hus:list){
			System.out.println(hus);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 一对一关联查询
	 * 方法四
	 */
	@Test
	public void select_hussWife3(){
		try{
		SqlSession session = MyBatisSqlSessionFactory.openSession(true);
		One2OneMapper oom = session.getMapper(One2OneMapper.class);
		List<Hus> list = oom.findHuss_wife3();
		for(Hus hus:list){
			System.out.println(hus);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 一对一关联查询
	 * 方法五
	 */
	@Test
	public void select_hussWife4(){
		try{
		SqlSession session = MyBatisSqlSessionFactory.openSession(true);
		One2OneMapper oom = session.getMapper(One2OneMapper.class);
		List<Hus> list = oom.findHuss_wife4();
		for(Hus hus:list){
			System.out.println(hus);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
