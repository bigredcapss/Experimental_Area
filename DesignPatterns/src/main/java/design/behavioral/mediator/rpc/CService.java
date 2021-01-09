package design.behavioral.mediator.rpc;

public class CService implements IService {
    Registy registy;
    CService(){
        registy.regist("cService",this);
    }


}
