package handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import bean.PhoneNumber;

/**
 * 字符串和对象之间的转换
 * @author WE
 *
 */
public class PhoneTypeHandler extends BaseTypeHandler<PhoneNumber>{

	//查询中遇到PhoneNumber类型的应该如何封装(使用列名封装)
	/**
	 * 将查询到的结果集封装成对象
	 * 第一个参数指查询到的结果集
	 * 第二个参数指字符串phone
	 */
	@Override
	public PhoneNumber getNullableResult(ResultSet arg0, String arg1) throws SQLException {
//		System.out.println(arg0+"--"+arg1);
		String phone = arg0.getString(arg1);
		//方案一：在这里手动拆分；方案二：在PhoneNumber里面重新定义构造器
//		String[] str = phone.split("[-]");//这里的中括号相当于转义字符,可以试着不加中括号，但这样做易出错
//	return new PhoneNumber(str[0], str[1], str[2]);
		//处理如果phone为空的情况
		if(phone!=null){
			return new PhoneNumber(phone);
		}
		return null;
		
	}

	//查询中遇到PhoneNumber类型的应该如何封装(使用列的下标)
	@Override
	public PhoneNumber getNullableResult(ResultSet arg0, int arg1) throws SQLException {
		System.out.println(arg0+"--"+arg1);
		return null;
	}

	//CallableStatement使用中遇到了PhoneNumber类型的应该如何封装
	@Override
	public PhoneNumber getNullableResult(CallableStatement arg0, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	
	//遇到PhoneNumber参数的时候应该如何在ps中设置值
	/**
	 * 将自定义的类型转化为可以设置到数据库的类型，即PhoneNumber-->String；
	 * 第一个参数PreparedStatement对象代表将来可以使用该对象把数据设置到数据库中；第二个参数代表占位符(?)的位置;第三个参数代表将来可以通过getPhone方法得到PhoneNumber类型;
	 * 第四个参数是JDBC的类型，设置了才有，没设置就没有，默认null值
	 */
	@Override
	public void setNonNullParameter(PreparedStatement ps, int index, PhoneNumber phone, JdbcType arg3)
			throws SQLException {
//		System.out.println(index+"--"+phone);
//		String str=phone.getCountryCode()+"-"+phone.getStateCode()+"-"+phone.getNumber();
		ps.setString(index, phone.setAsString());
		
	}
	

}
