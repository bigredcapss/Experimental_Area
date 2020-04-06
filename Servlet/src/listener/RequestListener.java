package listener;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
/**
 * 监听器里面的方法，都是由Tomcat去完成的，只要请求的时服务器的servlet,都会调用初始化，销毁方法
 * @author WE
 * 
    监听器(Listener)
	作用:监听web中的一些事件的发生,如果某些事件一旦发生了,那么这个监听器就会调用某些方法进行处理.
	比如:在web中可以监听request对象的创建和销毁.

	如何去写一个监听器:
	1.写一个类,实现一个特点的接口。
	2.在web.xml文件中进行配置。
	web.xml:
	 <listener>
  		<listener-class>listener.RequestListener</listener-class>
	 </listener>
	
	比如:
	监听request对象的创建和销毁要实现接口ServletRequestListener
	监听session对象的创建和销毁要实现接口HttpSessionListener
	监听application对象的创建和销毁要实现接口  


 *
 */
public class RequestListener implements ServletRequestListener{

	//request对象销毁时调用的方法
	public void requestDestroyed(ServletRequestEvent arg0) {
		//获取事件源，所谓事件源就是谁触发的
		ServletRequest request = arg0.getServletRequest();//这里request就是容器
		System.out.println("request destroyed.....");
		
	}

	//当Tomcat构建request对象的时候，初始化的方法
	public void requestInitialized(ServletRequestEvent arg0) {
		//获取事件源，所谓事件源就是谁触发的
	    ServletRequest request = arg0.getServletRequest();///这里request就是容器
	    System.out.println("request initial....");
		
	}
	
}