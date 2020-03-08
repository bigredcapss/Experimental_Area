package bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

/**
 * @Description:工人实体类
 * @Author:BigRedCaps
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Worker
{
    private int workerId;
    private String workerName;

    /*
     *一个工人，对应的多个地址
     */
    private Set<String> addressSet;//set集合映射

    private List<String> addressList = new ArrayList<String>();//List集合映射

    private String[] addressArray; // 数组映射：映射方式和list相同<array name=""></array>

    private Map<String,String> addressMap = new HashMap<String, String>();//Map集合映射
}
