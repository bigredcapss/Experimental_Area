package bean.compositekeymapping;

import bean.compositekeymapping.CompositeKeysBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:用户实体类
 * @Author:BigRedCaps
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User
{
    private CompositeKeysBean keys;
    private int age;
}
