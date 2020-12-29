package design.structuralmodel.adapter.general.interfaceadapter;

/**
 * 接口适配器测试类
 * 接口适配器：接口适配器与类适配器和对象适配器的关注点不一样，类适配器和对象适配器着重将系统中存在的一个角色（Adaptee）
 * 转化为目标接口（target）所需内容，而接口适配器的使用场景是解决接口方法过多，如果直接实现接口，那么类会多出很多空实现
 * 的方法，类显得很臃肿。此时，使用接口适配器就能让我们在客户端只实现我们需要的接口方法，目标更清晰。
 * @author BigRedCaps
 * @date 2020/12/27 21:16
 */
public class Test
{
    public static void main (String[] args)
    {
        Target target = new Adapter(new Adaptee()){
            @Override
            public int request1 ()
            {
                return adaptee.specificRequest()/10;
            }
        };
        int request1 = target.request1();
        System.out.println(request1);


    }

}
