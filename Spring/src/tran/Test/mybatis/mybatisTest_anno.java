package tran.Test.mybatis;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import db.Hus;
import tran.service.HusService;

public class mybatisTest_anno {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext cp=
				new ClassPathXmlApplicationContext(
						"tran/dao/mybatis/mybatis.xml",
						"tran/service/mybatis/mybatis_service_anno.xml");
		HusService service=(HusService) cp.getBean("service");
		service.saveHus(new Hus(6, "wangwu", 44));
	}
}
