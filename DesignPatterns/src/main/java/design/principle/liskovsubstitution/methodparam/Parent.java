package design.principle.liskovsubstitution.methodparam;

import java.util.HashMap;

/**
 * @Description:里氏替换原则的方法入参实例
 * @Author:BigRedCaps
 */
public class Parent
{
    //可以尝试把入参修改为Map类型,子类入参修改为HashMap类型,测试结果发生改变
    public void method(HashMap hashMap)
    {
        System.out.println("父类执行");
    }

}
