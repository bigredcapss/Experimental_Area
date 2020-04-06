package JDBC;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

/**
 * @Description:BeanUtils:操作Java属性的工具类；
 * @Author:BigRedCaps
 */
public class BeanUtilsTest
{
    @Test
    public void BeanUtilsTest() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException
    {
        Object object = new Student();

        BeanUtils.setProperty(object,"studentName","复兴");

        System.out.println(object);

        Object studentNameValue = BeanUtils.getProperty(object,"studentName");

        System.out.println(studentNameValue);
    }
}
