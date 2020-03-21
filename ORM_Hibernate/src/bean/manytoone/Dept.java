package bean.manytoone;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description:部门实体
 * @Author:BigRedCaps
 */
@NoArgsConstructor
@AllArgsConstructor
public class Dept
{
    private int deptId;

    private String deptName;

    // 【一对多】 一个部门对应多个员工
    private Set<Employee2> emps = new HashSet<>();

    public int getDeptId ()
    {
        return deptId;
    }

    public void setDeptId (int deptId)
    {
        this.deptId = deptId;
    }

    public String getDeptName ()
    {
        return deptName;
    }

    public void setDeptName (String deptName)
    {
        this.deptName = deptName;
    }

    public Set<Employee2> getEmps ()
    {
        return emps;
    }

    public void setEmps (Set<Employee2> emps)
    {
        this.emps = emps;
    }
}
