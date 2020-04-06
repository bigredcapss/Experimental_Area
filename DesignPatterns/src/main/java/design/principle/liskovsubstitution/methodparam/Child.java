package design.principle.liskovsubstitution.methodparam;

import java.util.Map;

/**
 * @Description:重载父类的方法时,子类的方法入参要比父类更加宽松
 * @Author:BigRedCaps
 */
public class Child extends Parent
{

    public void method(Map map){
        System.out.println("执行子类Map的入参方法");
    }
}
