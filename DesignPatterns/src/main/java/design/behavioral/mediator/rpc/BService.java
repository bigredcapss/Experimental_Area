package design.behavioral.mediator.rpc;


public class BService implements IService{
    Registy registy;
    BService(){
        registy.regist("bService",this);
    }
}
