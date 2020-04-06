package tran.Test.jdbc;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import db.Hus;
import tran.service.HusService;

public class jdbcTest_anno {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext cp=
				new ClassPathXmlApplicationContext(
						"tran/dao/jdbc/jdbc.xml",
						"tran/service/jdbc/jdbc_service_anno.xml");
		HusService service=(HusService) cp.getBean("service");
		service.saveHus(new Hus(4, "wangwu", 44));
	}
}
