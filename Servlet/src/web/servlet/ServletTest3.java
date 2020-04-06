package web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * b.客户端重定向
	   1.跳转到一个页面
	   2.跳转到另外一个servlet

	   重定向本质是：请求服务器资源，服务器资源告知浏览器请求新的资源。。。
		
	   String page = "页面/servlet";
	   resp.sendRedirect(page);
	特点:
	   1.跳转之后,浏览器中显示的是跳转到的那个资源(页面/servlet)的地址,而不是第一次访问的servlet地址.
	     即地址栏的地址会发生改变
	     重定向至少两次请求，用到的request和response都是新的。
	   2.客服端重定向,其实就是让客户端浏览器重新再发一次新的请求到要跳转的资源。
	     所以在客户端重定向中我们使用的对象是response,因为response这个对象的作用就是向客户端传递数据传递消息用的。
	   3.在客户端重定向的过程中,每个环节中所用到的request,response对象都是浏览器新发出的。

 */
@WebServlet("/ServletTest3")
public class ServletTest3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//http://localhost:8888/ServletStudy/ServletTest3
		response.sendRedirect("ServletTest4");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
