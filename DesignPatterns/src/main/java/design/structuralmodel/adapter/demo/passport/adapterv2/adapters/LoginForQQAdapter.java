package design.structuralmodel.adapter.demo.passport.adapterv2.adapters;

import design.structuralmodel.adapter.demo.passport.ResultMsg;

public class LoginForQQAdapter extends AbstraceAdapter{
    public boolean support(Object adapter) {
        return adapter instanceof LoginForQQAdapter;
    }

    public ResultMsg login(String id, Object adapter) {
        if(!support(adapter)){return null;}
        //accesseToken
        //time
        return super.loginForRegist(id,null);

    }

}
