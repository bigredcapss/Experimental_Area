package web.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * request session application三大容器对象，三个容器都维护了Map集合，分别对应HttpServletRequest,HttpSession,ServletContext；
 * 当然还有一个page(jsp页面内有效)，这里不做说明
 * 1.如何获取容器对象，如何设置值，取值
 * 2.EL表达式从容器中取出对象，简化JSP页面
 * 	El表达式专门从容器中取内容的
	语法：
	${容器.key}
	e.g.：${requestScope.name}
	后台			前台
	request			requestScope
	HttpSession		sessionScope
	ServletContext	applciationScope
Request:
生命周期：request始于浏览器终止于浏览器（浏览器每发出一次http请求，都会构建新的request对象）;request给浏览器传数据是一次性的，
 只能通过服务器内部跳转给页面传数据

 */
@WebServlet("/ContainerServlet")
public class ContainerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取Request对象(直接操作request即可)，向request容器设置值，取值
		request.setAttribute("name", "tom");
		Object name = request.getAttribute("name");
		System.out.println("HttpServletRequest对象："+name);
		//获取HttpSession对象，设置值，取值
		HttpSession session = request.getSession();
		session.setAttribute("age", 30);
		Object age = session.getAttribute("age");
		System.out.println("HttpSession对象："+age);
		//获取ServletContext对象，设置值，取值
		//方式一
//		ServletContext application = request.getServletContext();
		//方式二
//		ServletContext application = session.getServletContext();
		//方式三
		ServletContext application = this.getServletContext();
		application.setAttribute("Gender", "男");
		Object gender = application.getAttribute("Gender");
		System.out.println("ServletContext对象："+gender);
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
