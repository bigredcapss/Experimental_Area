package design.behavioral.delegate.simple;

/**
 * 数据爬虫工程师
 * @author BigRedCaps
 * @date 2020/12/27 11:23
 */
public class EmployeeB implements IEmployee
{
    protected String goodAt = "编程";
    @Override
    public void doing(String task) {
        System.out.println("我是员工B，我擅长" + goodAt + ",现在开始做" +task + "工作");
    }
}
