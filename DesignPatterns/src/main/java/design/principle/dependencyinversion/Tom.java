package design.principle.dependencyinversion;

/**
 * @Description:抽象不应该依赖细节，细节因该依赖抽象.针对接口编程，不要针对实现编程；
 * 该类是面向接口编程的关键
 * @Author:BigRedCaps
 */
public class Tom
{
    //=====  V1  ========
//    public void studyJavaCourse(){
//        System.out.println("Tom正在学习Java课程");
//    }
//
//    public void studyPythonCourse(){
//        System.out.println("Tom正在学习Python课程");
//    }
//
//    public void studyAICourse(){
//        System.out.println("Tom正在学习AI课程");
//    }

    //=====  V2  ========
//    public void study(ICourse course){
//        course.study();
//    }

    private ICourse iCourse;

    //=====  V3  ========
//    public Tom(ICourse iCourse) {
//        this.iCourse = iCourse;
//    }

    //=====  V4  ========
    public void setICourse(ICourse iCourse) {
        this.iCourse = iCourse;
    }

    public void study(){
        iCourse.study();
    }
}
