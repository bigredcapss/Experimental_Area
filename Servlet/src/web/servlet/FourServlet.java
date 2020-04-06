package web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 1.该注解等价与web.xml中的配置
 *<servlet>
    <servlet-name>four</servlet-name>
    <servlet-class>web.servlet.FourServlet</servlet-class>
  </servlet>
  <servlet-mapping>
    <servlet-name>four</servlet-name>
    <url-pattern>/FourSer</url-pattern>
  </servlet-mapping>
  注解，Tomcat启动项目的时候，会自动扫描所有的Serlvet类，有注解会把注解和Servlet类关联记录；该注解也可以这样写
  @WebServlet(urlPatterns={"/FourSer","/FS"},name="FourServlet")
  loadOnStartup表示该Servlet对象会随Tomcat的启动而创建，数值表示创建的优先级，越小优先级越高
  
  2.Servlet的生命周期
   servlet的对象是单例:在web项目整个运行期间,每一个servlet类只会有一个对象存在.默认情况下,这一个对象是在servlet第一次被访问的时候才会创建的,而且之后不管再访问多少次这个servlet,
   也不会再去创建它的的对象的.(我们可以设置某一个servlet的对象在服务器启动的时候就被创建出来)  
         这些操作(方法的调用)都是由服务器来做的:
     1.默认情况下,第一次被访问的时候tomcat创建servlet对象(调用无参构造器)
	 2.tomcat调用init(ServletConfig config)方法
	      在servlet对象实例化之后,tomcat服务器会立马调用这个方法给servlet的实例进行初始化工作。
	 3.客户端访问的时候,tomcat会调用service(ServletRequest req,ServletResponse res)方法
	 4.在销毁servlet对象的时候会tomcat调用destroy()方法.Tomcat正常关闭时
3.Servlet的线程安全问题
	问题产生的原因:
	  1.servlet是单例,一个servlet类只有一个对象在项目运行期间。
	  2.web项目项目本身就有多线程的特点,虽然我们在代码中没有写和线程相关的东西,但是这个多线程的特点是由服务器给我们提供出来的,一个客户端发送了一个请求,服务器接收到请求后就会建一个线程去处理这个请求。
	所以就有可能出现这样的情况:俩个线程同时去访问同一个servlet对象.   
	如何解决/防止
	  1.加锁synchronized
	  2.尽量少的定义成员变量
	      因为只有成员变量才会产生线程安全的问题(在多个线程访问同一个对象的时候),方法中的局部变量是没有这样的问题的.
      3.其他(实现一些安全性的接口)
4.客户端向服务器发请求并且传参(get/post)
	客户端向服务器发送请求可以是get方式也可以是post方式.所以传参也分为get方式下传参和post方式下传参.
	1.哪些方式的请求属于get/post方式
	    get方式:
	       a.浏览器中输入地址(URL)然后回车
	       b.超链接
	       c.页面中引入的css样式文件
	       d.页面中引入的js的文件(javascript)
	       e.<img src="image/a.jpg" />
	       f.form表单中method="get"
	       g.ajax中可以设置异步提交请求的方式为get
	       f.其他
	       
	     post方式:
	       a.form表单中method="post"
	       b.ajax中可以设置异步提交请求的方式为post
	       c.其他

	 2.get和post的特点及其区别
	    它们各自的特点及其区别主要是体现在所传递的参数上面。

	    a.get方式参数
		参数是直接放在要请求url后面的
		例如:
     url?参数名=参数值&参数名=参数值
     要请求的url为:
     http://ip:port/WebTest/firstServlet
     传参:
     .../firstServlet?name=tom
     .../firstServlet?name=tom&age=20

                这种方式传参的特点:
		   1.参数直接放在url后面
		   2.从浏览器的地址栏里面可以直接看到所传的参数
		   3.参数的长度有限制,不能把一个很长的数据通过get方式传参.(与浏览器种类有关)

	     b.post方式传参
		参数是放在请求的体部的。
		(浏览器发送的请求可以大致的分为请求头+请求体)

		这种方式传参的特点:
		   1.参数放在请求的体部而不是url后面.
		   2.浏览器的地址栏中看不到所传的参数.
		   3.因为参数不用出现在地址栏里面,所有参数长度是没有限制的.

  
 */
@WebServlet(urlPatterns={"/FourSer"},loadOnStartup=2)
public class FourServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public FourServlet() {
        System.out.println("FourServlet create");
    }

    public void init(){
    	System.out.println("对Servlet实例进行初始化工作");
    }
    
    public void destroy(){
    	System.out.println("destroy......");
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("调用了service方法");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
