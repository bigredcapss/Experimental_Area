package design.behavioral.chain.general;

/**
 * 责任链模式测试类
 * @author BigRedCaps
 * @date 2020/12/30 19:53
 */
public class Test
{
    public static void main (String[] args)
    {
        Handler handlerA = new ConcreteHandlerA();
        Handler handlerB = new ConcreteHandlerB();
        handlerA.setNextHandler(handlerB);
        handlerA.handleRequest("requestB");

    }

}
