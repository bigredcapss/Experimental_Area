package design.principle.simpleresponsibility.simple;

/**
 * @Description:
 * @Author:BigRedCaps
 */
public class Course
{
    public void study(String courseName)
    {
        if("直播课".equals(courseName))
        {
            System.out.println("不能快进！");
        }
        else
        {
            System.out.println("可任意的来回播放");
        }
    }
}
