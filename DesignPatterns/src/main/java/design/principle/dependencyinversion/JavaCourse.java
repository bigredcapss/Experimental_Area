package design.principle.dependencyinversion;

/**
 * @Description:学习Java课程
 * @Author:BigRedCaps
 */
public class JavaCourse implements ICourse
{
    @Override
    public void study ()
    {
        System.out.println("Tom正在学习Java课程");
    }
}
