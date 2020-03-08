package bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description:部门实体
 * @Author:BigRedCaps
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dept
{
    private int deptId;

    private String deptName;

    // 【一对多】 一个部门对应多个员工
    private Set<Employee2> empSet = new HashSet<>();
}
