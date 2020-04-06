package convert;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 自定义一个类型转换器类
 * @author WE
 *
 */
public class StringToDateConverter implements Converter<String, Date>{

	@Override
	public Date convert(String arg0) {
		SimpleDateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = dateFormate.parse(arg0);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

}
