package design.principle.dependencyinversion;

/**
 * @Description:依赖倒置原则测试
 * @Author:BigRedCaps
 */
public class DiTest
{
    public static void main (String[] args)
    {
        //=====  V1  ========
//        Tom tom = new Tom();
//        tom.studyJavaCourse();
//        tom.studyPythonCourse();
//        tom.studyAICourse();


        //=====  V2  ========
//        Tom tom = new Tom();
//        tom.study(new JavaCourse());
//        tom.study(new PythonCourse());


        //=====  V3  ========
//        Tom tom = new Tom(new JavaCourse());
//        tom.study();


        //=====  V4  ========
        Tom tom = new Tom();
        tom.setICourse(new JavaCourse());
        tom.study();
    }
}
