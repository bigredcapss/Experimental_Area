package bean;

import org.springframework.beans.propertyeditors.PropertiesEditor;

/**
 * 自定义编辑器
 * String-->Object
 * @author WE
 *
 */
public class EditorAddress extends PropertiesEditor{
	private Object value;
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}

	/**
	 * 转化的方法，spring自动调用，再将String类型转换为对象的过中，spring自动调用setAsTest()方法，参数就是解析得到的value属性;
	 * 
	 * Spring会调用getValue方法获取封装好的对象
	 */
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		String[] str = text.split("[,]");
		Address addr = new Address();
		addr.setProvince(str[0]);
		addr.setCity(str[1]);
		addr.setStreet(str[2]);
		setValue(addr);
	}

}
