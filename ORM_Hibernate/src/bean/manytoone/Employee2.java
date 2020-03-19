package bean.manytoone;

import lombok.*;

/**
 * @Description:员工实体
 * @Author:BigRedCaps
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Employee2
{
    private int empId;

    private String empName;

    private double salary;

    // 【多对一】多个员工与一个部门
    private Dept dept;

}
