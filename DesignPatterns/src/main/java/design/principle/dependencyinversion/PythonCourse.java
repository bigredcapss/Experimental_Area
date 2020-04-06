package design.principle.dependencyinversion;

/**
 * @Description:学习Python课程
 * @Author:BigRedCaps
 */
public class PythonCourse implements ICourse
{
    @Override
    public void study ()
    {
        System.out.println("Tom正在学习Python课程");
    }
}
