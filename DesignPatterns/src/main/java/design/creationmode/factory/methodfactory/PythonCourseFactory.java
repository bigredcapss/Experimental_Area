package design.creationmode.factory.methodfactory;

import design.creationmode.factory.ICourse;
import design.creationmode.factory.PythonCourse;

/**
 * Python课程工厂
 */
public class PythonCourseFactory implements ICourseFactory {

    public ICourse create() {
        return new PythonCourse();
    }
}
