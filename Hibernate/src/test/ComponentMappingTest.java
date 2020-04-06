package test;

import bean.componentmapping.Car;
import bean.componentmapping.Wheel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

/**
 * @Description:汽车与轮子的组件映射测试
 * @Author:BigRedCaps
 */
public class ComponentMappingTest
{
    private static SessionFactory sf;
    static {
        sf = new Configuration()
                .configure()
                .addClass(Car.class)
                .buildSessionFactory();
    }

    @Test
    public void saveTest() {
        Session session = sf.openSession();
        session.beginTransaction();

        // 轮子
        Wheel wheel = new Wheel();
        wheel.setSize(38);
        wheel.setCount(4);
        // 汽车
        Car car = new Car();
        car.setName("BMW");
        car.setWheel(wheel);

        // 保存
        session.save(car);

        session.getTransaction().commit();
        session.close();

    }
}
