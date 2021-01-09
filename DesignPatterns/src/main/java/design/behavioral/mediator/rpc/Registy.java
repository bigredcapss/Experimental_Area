package design.behavioral.mediator.rpc;

/**
 *
 * //RPC    Remote Procedure Call  远程过程调用
 *
 * 伪代码
 */
public class Registy {
    //通过中介者缩短调用链
    //服务治理
    public boolean regist(String serviceName,IService service){
        return true;
    }

    public IService get(String serviceName){
        return null;
    }
}
