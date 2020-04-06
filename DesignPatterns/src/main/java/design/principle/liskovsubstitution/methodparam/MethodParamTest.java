package design.principle.liskovsubstitution.methodparam;

import java.util.HashMap;

/**
 * @Description:
 * @Author:BigRedCaps
 */
public class MethodParamTest
{
    public static void main (String[] args)
    {
        Parent child = new Child();
        HashMap hashMap = new HashMap();
        child.method(hashMap);
    }
}
