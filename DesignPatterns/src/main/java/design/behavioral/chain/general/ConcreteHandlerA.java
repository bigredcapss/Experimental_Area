package design.behavioral.chain.general;

/**
 * 具体处理者：对请求进行处理，如果不敢兴趣，则进行转发
 * @author BigRedCaps
 * @date 2020/12/30 19:43
 */
public class ConcreteHandlerA extends Handler
{
    @Override
    protected void handleRequest (String request)
    {
        if("requestA".equals(request))
        {
            System.out.println(this.getClass().getSimpleName()+" deal with request:"+request);
            return;
        }
        // 不感兴趣，转发该请求
        if(this.nextHandler!=null)
        {
            this.nextHandler.handleRequest(request);
        }
    }
}
