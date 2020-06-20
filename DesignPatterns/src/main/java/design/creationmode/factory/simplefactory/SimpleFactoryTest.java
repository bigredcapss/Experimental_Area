package design.creationmode.factory.simplefactory;

import design.creationmode.factory.ICourse;
import design.creationmode.factory.JavaCourse;

/**
 * 简单工厂模式测试
 *
 * 以课程工厂为例：
 * 简单工厂模式：工厂刚开始营业，工厂加工Java,Python两种课程，两种课程的功能也比较单一，只提供录制视频功能；
 */
public class SimpleFactoryTest {

    public static void main(String[] args) {

//        ICourse course = new JavaCourse();
//        course.record();

//        ICourseFactory factory = new ICourseFactory();
//        ICourse course = factory.create("com.gupaoedu.vip.pattern.factory.JavaCourse");
//        course.record();

        CourseFactory factory = new CourseFactory();
        ICourse course = factory.create(JavaCourse.class);
        course.record();

    }
}
