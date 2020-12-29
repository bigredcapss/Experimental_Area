package design.structuralmodel.adapter.demo.passport.adapterv2.adapters;


import design.structuralmodel.adapter.demo.passport.ResultMsg;

public class LoginForTokenAdapter extends AbstraceAdapter {
    public boolean support(Object adapter) {
        return adapter instanceof LoginForTokenAdapter;
    }

    public ResultMsg login(String id, Object adapter) {
        return super.loginForRegist(id,null);
    }

}
