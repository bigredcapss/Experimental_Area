package bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:员工实体
 * @Author:BigRedCaps
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee2
{
    private int empId;

    private String empName;

    private double salary;

    // 【多对一】多个员工与一个部门
    private Dept dept;


}
