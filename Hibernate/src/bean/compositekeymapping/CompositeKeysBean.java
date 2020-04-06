package bean.compositekeymapping;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description:复合主键类;该类必须实现序列化接口；
 * @Author:BigRedCaps
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompositeKeysBean implements Serializable
{
    //用户名和地址唯一确定一条记录
    private String userName;
    private String address;
}
