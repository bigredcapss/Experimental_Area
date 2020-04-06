package bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SrcBean {
	/**
	 * 基本数据类型或字符串用@Value注解
	 * 位置：set方法上赋值，全局变量上赋值
	 */
	@Value(value = "1")
	private int id;
	

	@Override
	public String toString() {
		return "SrcBean [id=" + id + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	

}
