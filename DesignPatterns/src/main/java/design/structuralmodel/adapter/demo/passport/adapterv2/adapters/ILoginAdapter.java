package design.structuralmodel.adapter.demo.passport.adapterv2.adapters;

import design.structuralmodel.adapter.demo.passport.ResultMsg;

public interface ILoginAdapter {
    boolean support (Object object);
    ResultMsg login (String id, Object adapter);
}
