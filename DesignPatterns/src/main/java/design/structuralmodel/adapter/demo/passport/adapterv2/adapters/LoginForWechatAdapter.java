package design.structuralmodel.adapter.demo.passport.adapterv2.adapters;


import design.structuralmodel.adapter.demo.passport.ResultMsg;

public class LoginForWechatAdapter extends AbstraceAdapter{
    public boolean support(Object adapter) {
        return adapter instanceof LoginForWechatAdapter;
    }

    public ResultMsg login(String id, Object adapter) {
        return super.loginForRegist(id,null);
    }


}
