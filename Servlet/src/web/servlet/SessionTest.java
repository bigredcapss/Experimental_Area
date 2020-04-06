package web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * HttpSession	    session
       获取对象：
       HttpSession session = request.getSession();
       设置值：
       session.setAttribute(key,value);
       取值：
       session.getAttribute(key)；
       范围：
       session存储的都是用户的私人信息

       生命周期：默认的是浏览器和服务器三十分钟之内没有交互，服务器自动销毁session对象及里面信息
       a:session创建：第一次访问非静态资源时创建，浏览器jessionod存在，服务器sessionid销毁，服务器重新创建
       b:浏览器jessionod不存在，服务器sessionid存在，也是重新创建（服务器端session没有销毁）
	   c:服务器正常关闭session的所有对象序列化保存到work目录，启动反序列化得到对象
       d:session对象对同一个浏览器访问的任意资源都共享
       特点：http协议是无状态协议（不记录是哪个浏览器访问的）需要服务器给出一种机制（标记），
             来标记是哪个浏览器访问的
	     浏览器访问非静态资源（servlet，jsp），服务器会给该浏览器创建一个session对象，
	     当前请求回写资源给浏览器时，会把session的id带给浏览器，浏览器把session的id保存到浏览器
	     中的cookie里面，同时会标记该项目及失效时间，下一次只要访问该项目都会把session的id发给服务器
	     （session的id在本地以jsessionid出现）

 */
@WebServlet("/SessionTest")
public class SessionTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session	= request.getSession();
	    String id = session.getId();
	    System.out.println("SessionID:"+id);
	    Object num = session.getAttribute("num");
		if(num == null){
			session.setAttribute("num", 1);
		}else{
			int n = (int) num;
			session.setAttribute("num", n+1);
		}
		request.getRequestDispatcher("/test.jsp").forward(request, response);

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
