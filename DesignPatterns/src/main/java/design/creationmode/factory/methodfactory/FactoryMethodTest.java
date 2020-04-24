package design.creationmode.factory.methodfactory;


import design.creationmode.factory.ICourse;

/**
 * 方法工厂测试
 */
public class FactoryMethodTest {

    public static void main(String[] args) {

        ICourseFactory factory = new PythonCourseFactory();
        ICourse course = factory.create();
        course.record();

        factory = new JavaCourseFactory();
        course = factory.create();
        course.record();

    }

}
