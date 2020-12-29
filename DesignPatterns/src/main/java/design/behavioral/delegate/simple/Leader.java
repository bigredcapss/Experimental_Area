package design.behavioral.delegate.simple;

import java.util.HashMap;
import java.util.Map;

/**
 * 经理角色--进行具体的任务委派
 * @author BigRedCaps
 * @date 2020/12/27 11:18
 */
public class Leader implements IEmployee
{
    private Map<String,IEmployee> employee = new HashMap<>();

    public Leader ()
    {
        employee.put("海报设计",new EmployeeA());
        employee.put("爬数据",new EmployeeB());
    }

    @Override
    public void doing (String task)
    {
        if(!employee.containsKey(task)){
            System.out.println("这个任务"+task+"超出了我的工作范畴");
            return;
        }
        employee.get(task).doing(task);
    }
}
