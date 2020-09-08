package reflect.bean;

import lombok.Data;

/**
 * @Description:
 * @Author:BigRedCaps
 */
@Data
public class Person
{
    private String name;

    @Override
    public String toString ()
    {
        return "I am a person, my name is " + name;
    }
}
