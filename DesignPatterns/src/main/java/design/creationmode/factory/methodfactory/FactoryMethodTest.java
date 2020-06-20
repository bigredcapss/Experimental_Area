package design.creationmode.factory.methodfactory;


import design.creationmode.factory.ICourse;

/**
 * 方法工厂测试
 *
 * 方法工厂模式：随着工厂的发展，效益增加，把Java,Pyhton单独独立出来，作为子工厂；
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
