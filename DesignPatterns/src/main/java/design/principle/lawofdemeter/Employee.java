package design.principle.lawofdemeter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author:BigRedCaps
 */
public class Employee
{
    public void checkNumberOfCourse()
    {
        List<Course> courseList = new ArrayList<>();
        for (int i = 0; i < 20 ; i++)
        {
            courseList.add(new Course());
        }
        System.out.println("已发布的课程数："+courseList.size());
    }
}
