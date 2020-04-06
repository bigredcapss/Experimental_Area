package JDBC;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description:
 * @Author:BigRedCaps
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customers
{
    private int id;
    private String name;
    private String email;
    private Date birth;
}
