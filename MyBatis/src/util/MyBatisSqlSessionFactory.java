package util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisSqlSessionFactory {
	private static SqlSessionFactory factory = null;
	private static SqlSessionFactory getFactory(){
		if(factory==null){
			//1 2号线程
			synchronized (MyBatisSqlSessionFactory.class) {
				if(factory==null){
					try {
						InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
						//指令重排序问题
						factory = new SqlSessionFactoryBuilder().build(is);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	return factory;
	}
	
	public static SqlSession openSession(boolean autoCommit){
		//openSession方法没有传入参数的时侯，默认false，手动提交事务
		return getFactory().openSession(autoCommit);
	}
	
	public static SqlSession openSession(){
		return openSession(false);
	}


}
