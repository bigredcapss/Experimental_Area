package listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @WebListener等价与web.xml配置中的
  <listener>
  <listener-class>listener.RequestListener</listener-class>
  </listener>
 *
 */
@WebListener
public class ListtenerTest implements HttpSessionListener {
	/**
	 * sesssion创建的时候调用的方法;session的创建时间：第一次访问项目中的资源时都会创建session
	 */
    public void sessionCreated(HttpSessionEvent arg0)  { 
    	//里面同样可以获取session对象，设置session的生命周期
    	HttpSession session = arg0.getSession();
         System.out.println("session Created...");
    }
    public void sessionDestroyed(HttpSessionEvent arg0)  { 
    	System.out.println("session destroyed......");
    }
	
}
