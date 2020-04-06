package aop.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 方法的处理器(针对整个切面)
 * @author WE
 *
 */
public class MyHandler implements InvocationHandler{
	//目标对象的实例
	private Object target;

	/**
	 * 第一个参数：代理对象
	 * 第二个参数：方法的目标对象的镜像
	 * 第三个参数：表示目标对象中方法的参数
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("before.....");
		/*
		 * 作用：调用该镜像所对应的方法
		 * 第一个参数：目标对象的实例
		 * 第二个参数：目标对象的参数列表
		 */
		Object obj = method.invoke(target, args);
		System.out.println("after......");
		return obj;
	}

	public Object getTarget() {
		return target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}
	

}
