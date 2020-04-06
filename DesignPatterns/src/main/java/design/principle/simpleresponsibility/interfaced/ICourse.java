package design.principle.simpleresponsibility.interfaced;

/**
 * @Description:
 * @Author:BigRedCaps
 */
public interface ICourse
{
    //课程展示
    String getCourseName();
    byte[] getCourseVideo();

    //课程控制
    void studyCourse();
    void refundCourse();
}
