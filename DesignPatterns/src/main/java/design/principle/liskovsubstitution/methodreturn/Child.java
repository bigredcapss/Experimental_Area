package design.principle.liskovsubstitution.methodreturn;

import java.util.HashMap;

/**
 * @Description:
 * @Author:BigRedCaps
 */
public class Child extends Parent
{
    //可以尝试修改method的返回类型,发现报错,可以猜测idea编译器在进行语法检查时同样遵循里氏替换原则
    public HashMap method ()
    {
        HashMap hashMap = new HashMap();
        System.out.println("执行子类的method");
        hashMap.put("msg","子类method");
        return hashMap;
    }
}
