package bean;

import java.io.InputStream;
import java.util.Properties;
/**
 * 构建bean实例的方式三：实例工厂方法构建
 * @author WE
 *
 */
public class InstanceFactory {
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserDao getDao(){
	Properties pro = new Properties();
	UserDao ud = null;
	System.out.println(name+"****");
	try {
		InputStream is = StaticFactory.class.getResourceAsStream("../ioc/staticF.properties");
		pro.load(is);
		ud = (UserDao) Class.forName(pro.getProperty("dao")).newInstance();
	} catch (Exception e) {
		e.printStackTrace();
	} 
	return ud;
	
}

}
