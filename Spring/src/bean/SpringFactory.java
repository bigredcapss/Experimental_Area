package bean;

import org.springframework.beans.factory.FactoryBean;
/**
 * 方式二：使用spring提供的工厂构建Bean实例
 * @author WE
 *
 */
public class SpringFactory implements FactoryBean<String>{

	/**
	 * 工厂方法
	 */
	@Override
	public String getObject() throws Exception {
		return "test..ok";
	}

	/**
	 * 产生工厂返回对象的镜像
	 */
	@Override
	public Class<?> getObjectType() {
		return null;
	}

}
