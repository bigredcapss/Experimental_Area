package tran.Test.mybatis;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import db.Hus;
import tran.service.HusService;

public class mybatisTest {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext cp=
				new ClassPathXmlApplicationContext(
						"tran/dao/mybatis/mybatis.xml",
						"tran/service/mybatis/mybatis_service.xml");
		HusService service=(HusService) cp.getBean("service");
		service.saveHus(new Hus(5, "wangwu", 44));
	}
}
