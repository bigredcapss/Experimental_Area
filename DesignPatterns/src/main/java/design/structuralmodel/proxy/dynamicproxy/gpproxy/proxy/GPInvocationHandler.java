package design.structuralmodel.proxy.dynamicproxy.gpproxy.proxy;

import java.lang.reflect.Method;

/**
 * 自定义InvocationHandler接口
 */
public interface GPInvocationHandler {
    public Object invoke (Object proxy, Method method, Object[] args)
            throws Throwable;
}
