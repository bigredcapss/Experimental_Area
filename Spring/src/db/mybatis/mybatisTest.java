package db.mybatis;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import db.Hus;
import db.HusDao;

public class mybatisTest {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext cp=
				new ClassPathXmlApplicationContext(
						"db/mybatis/mybatis.xml");
		HusDao dao=(HusDao) cp.getBean("husDao");
		dao.updateHus(new Hus(1, "jake", 100));
	}
}
