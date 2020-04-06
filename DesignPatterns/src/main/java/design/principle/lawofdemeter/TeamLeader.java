package design.principle.lawofdemeter;

/**
 * @Description:
 * @Author:BigRedCaps
 */
public class TeamLeader
{
    public void commandCheckCourseNumber(Employee employee)
    {
        employee.checkNumberOfCourse();
    }
}
