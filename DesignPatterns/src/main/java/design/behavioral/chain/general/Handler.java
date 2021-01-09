package design.behavioral.chain.general;

/**
 * 责任链模式--抽象处理者角色：定义一个请求处理的方法，并维护一个下一个处理节点Handler对象的引用
 * @author BigRedCaps
 * @date 2020/12/30 19:42
 */
public abstract class Handler
{
    protected Handler nextHandler;

    public void setNextHandler(Handler successor){
        this.nextHandler = successor;
    }

    protected abstract void handleRequest(String request);
}
