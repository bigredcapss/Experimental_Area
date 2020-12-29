package design.behavioral.delegate.simple;

/**
 * 平面设计工程师
 * @author BigRedCaps
 * @date 2020/12/27 11:22
 */
public class EmployeeA implements IEmployee
{
    protected String goodAt = "平面设计";
    @Override
    public void doing(String task) {
        System.out.println("我是员工A，我擅长" + goodAt + ",现在开始做" +task + "工作");
    }
}
