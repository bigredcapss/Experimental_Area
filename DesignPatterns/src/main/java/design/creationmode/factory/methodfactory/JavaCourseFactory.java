package design.creationmode.factory.methodfactory;

import design.creationmode.factory.ICourse;
import design.creationmode.factory.JavaCourse;

/**
 * Java课程工厂
 */
public class JavaCourseFactory implements ICourseFactory {
    public ICourse create() {
        return new JavaCourse();
    }
}
