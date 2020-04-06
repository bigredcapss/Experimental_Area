package listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 
 *
 */
@WebListener
public class ApliListener implements ServletContextListener {
	//Tomcat关闭的时候会调用该销毁方法
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    	System.out.println("contextDestroyed......");
    }
    /**
     * Tomcat创建application对象的时候，调用的方法，存储用户共享的数据(商品信息)，当商品信息过多的时候，就不能使用application对象了，需要使用缓存数据库(redis)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    	ServletContext sc = arg0.getServletContext();
    	sc.setAttribute("phone", "1234");
    	System.out.println("ContextInitialized......");
    	
    	
    	
    }
	
}
