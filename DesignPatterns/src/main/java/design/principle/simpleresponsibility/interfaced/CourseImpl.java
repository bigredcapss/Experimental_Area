package design.principle.simpleresponsibility.interfaced;

/**
 * @Description:
 * @Author:BigRedCaps
 */
public class CourseImpl implements ICourseInfo,ICourseManager
{
    @Override
    public void studyCourse ()
    {

    }

    @Override
    public void refundCourse ()
    {

    }

    @Override
    public String getCourseName ()
    {
        return null;
    }

    @Override
    public byte[] getCourseVideo ()
    {
        return new byte[0];
    }
}
