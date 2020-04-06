package aop.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
/**
 * 代理：在目标方法执行之前做预处理（e.g:日志输出）
 *     在目标方法执行之后再写代码（e.g:日志输出）
 */
import org.springframework.beans.factory.FactoryBean;

public class JdkProxyFactory implements FactoryBean<Object>{
	private Object target;//目标对象
	private Class[] interfaces;//目标对象实现的所有接口
	private InvocationHandler handler;//对目标对象的方法的处理器

	@Override
	public Object getObject() throws Exception {
		// TODO Auto-generated method stub
		Object proxy = Proxy.newProxyInstance(target.getClass().getClassLoader(), interfaces, handler);
		return proxy;
	}

	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getTarget() {
		return target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}

	public Class[] getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(Class[] interfaces) {
		this.interfaces = interfaces;
	}

	public InvocationHandler getHandler() {
		return handler;
	}

	public void setHandler(InvocationHandler handler) {
		this.handler = handler;
	}
	

}
