package tran.Test.jdbc;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import db.Hus;
import tran.service.HusService;

public class jdbcTest {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext cp=
				new ClassPathXmlApplicationContext(
						"tran/dao/jdbc/jdbc.xml",
						"tran/service/jdbc/jdbc_service.xml");
		HusService service=(HusService) cp.getBean("service");
		service.saveHus(new Hus(14, "wangwu", 44));
	}
}
