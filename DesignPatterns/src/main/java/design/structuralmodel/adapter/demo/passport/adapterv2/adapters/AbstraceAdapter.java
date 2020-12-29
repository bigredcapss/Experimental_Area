package design.structuralmodel.adapter.demo.passport.adapterv2.adapters;


import design.structuralmodel.adapter.demo.passport.PassportService;
import design.structuralmodel.adapter.demo.passport.ResultMsg;

public abstract class AbstraceAdapter extends PassportService implements ILoginAdapter {
    protected ResultMsg loginForRegist(String username, String password){
        if(null == password){
            password = "THIRD_EMPTY";
        }
        super.regist(username,password);
        return super.login(username,password);
    }
}
