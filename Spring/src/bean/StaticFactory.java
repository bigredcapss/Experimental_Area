package bean;

import java.io.FileReader;
import java.io.InputStream;
import java.util.Properties;

/**
 * 创建bean实例的方式二：静态工厂方法
 * @author WE
 *
 */
public class StaticFactory {
	
	/**
	 * 方法一
	 * @return
	 */
//	public static UserService getSer(){
//		Properties pro = new Properties();
//		UserService us = null;
//		try {
//			pro.load(new FileReader("src/ioc/staticF.properties"));
//			us = (UserService) Class.forName(pro.getProperty("service")).newInstance();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} 
//		return us;
//		
//	}
	/**
	 * 方法二
	 * @return
	 */
	private static Properties pro = null;
	public static UserService getSer(){
		UserService us = null;
		pro = new Properties();
		try {
			if(pro==null){
			synchronized (StaticFactory.class) {
			if(pro==null){
				//java项目有src目录，web项目没有src目录；因此用相对当前位置读取文件
				pro.load(new FileReader("src/ioc/staticF.properties"));
				//相对当前位置读取文件
				InputStream is = StaticFactory.class.getResourceAsStream("../ioc/staticF.properties");
				pro.load(is);
			}
			}
			}
			us = (UserService) Class.forName(pro.getProperty("service")).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return us;
		
	}
	
	

}
