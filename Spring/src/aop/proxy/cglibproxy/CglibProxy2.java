package aop.proxy.cglibproxy;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class CglibProxy2 implements MethodInterceptor{
	private Object target;//目标对象
	/**
	 * 自定义方法 构建代理对象
	 * 方法如果传参数，参数是目标对象的镜像;Cglib构建代理对象：把目标对象作为代理对象的父类去用
	 */
	public Object getProxy(){
		Enhancer en = new Enhancer();
		//将目标对象设置为父类
		en.setSuperclass(target.getClass());
		//回调函数：代理对象调用目标对象的方法时侯执行当前类的intercept()方法
		en.setCallback(this);
		return en.create();
		
	}

	/**
	 * 代理对象执行和目标对象相同的方法时，直接进入intercept()方法
	 * 第一个参数：代理对象
	 * 第二个参数：目标对象方法的镜像
	 * 第三个参数：方法的参数
	 * 第四个参数：代理对象方法的镜像
	 */
	@Override
	public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy arg3) throws Throwable {
		System.out.println(arg1+"before...."+Arrays.toString(arg2));
		//Object obj = arg3.invokeSuper(arg0, arg2);//方式一：通过代理对象中方法的代理去带调用父类目标对象中的方法
		Object obj = arg1.invoke(target, arg2);
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
