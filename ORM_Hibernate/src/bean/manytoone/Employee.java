package bean.manytoone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description:员工实体s
 * @Author:BigRedCaps
 */
@NoArgsConstructor
@AllArgsConstructor
public class Employee
{
    private int empId;
    private String empName;
    private Date workDate;

    public int getEmpId ()
    {
        return empId;
    }

    public void setEmpId (int empId)
    {
        this.empId = empId;
    }

    public String getEmpName ()
    {
        return empName;
    }

    public void setEmpName (String empName)
    {
        this.empName = empName;
    }

    public Date getWorkDate ()
    {
        return workDate;
    }

    public void setWorkDate (Date workDate)
    {
        this.workDate = workDate;
    }
}
