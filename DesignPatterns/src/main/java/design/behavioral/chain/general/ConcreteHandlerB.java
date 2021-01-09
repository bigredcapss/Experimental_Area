package design.behavioral.chain.general;

/**
 * 具体处理者B
 * @author BigRedCaps
 * @date 2020/12/30 19:48
 */
public class ConcreteHandlerB extends Handler
{
    @Override
    protected void handleRequest (String request)
    {
        if ("requestB".equals(request)) {
            System.out.println(this.getClass().getSimpleName() + " deal with request: " + request);
            return;
        }
        if (this.nextHandler != null) {
            this.nextHandler.handleRequest(request);
        }
    }
}
