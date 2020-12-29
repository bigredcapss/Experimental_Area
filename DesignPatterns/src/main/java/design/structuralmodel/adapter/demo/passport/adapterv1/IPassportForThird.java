package design.structuralmodel.adapter.demo.passport.adapterv1;

import design.structuralmodel.adapter.demo.passport.ResultMsg;


public interface IPassportForThird {

    ResultMsg loginForQQ (String openId);

    ResultMsg loginForWechat (String openId);

    ResultMsg loginForToken (String token);

    ResultMsg loginForTelphone (String phone, String code);

}
