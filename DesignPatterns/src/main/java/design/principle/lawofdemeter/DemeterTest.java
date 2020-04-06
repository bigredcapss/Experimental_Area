package design.principle.lawofdemeter;

/**
 * @Description:
 * @Author:BigRedCaps
 */
public class DemeterTest
{
    public static void main (String[] args)
    {
        TeamLeader teamLeader = new TeamLeader();
        Employee employee = new Employee();
        teamLeader.commandCheckCourseNumber(employee);
    }
}
