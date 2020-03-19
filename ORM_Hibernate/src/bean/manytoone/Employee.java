package bean.manytoone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description:员工实体
 * @Author:BigRedCaps
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee
{
    private int empId;
    private String empName;
    private Date workDate;
}
