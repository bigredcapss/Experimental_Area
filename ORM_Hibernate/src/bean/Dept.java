package bean;

import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description:部门实体
 * @Author:BigRedCaps
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Dept
{
    private int deptId;

    private String deptName;

    // 【一对多】 一个部门对应多个员工
    private Set<Employee2> emps = new HashSet<>();
}
