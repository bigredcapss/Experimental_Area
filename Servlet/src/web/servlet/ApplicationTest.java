package web.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * application对象对所有浏览器都是共享的
 *  特点：存储的是公共数据（大家共享的数据),每一个web项目只有一个application对象
	     整个项目中任何页面或servlet共享该对象
          生命周期：application对象由Tomcat创建，并且Tomcat关闭时销毁
          作用范围:整个项目在运行期间,只有一个application对象,所以这个对象是所有人所有会员共享的,大家都可以向这个对象里面存值,也可以再拿出来.因为 这个对象一个项目中只有一个.
         作用范围: request<session<application
  注意:getParameter("key")方法和getAttribute("key")方法,getParameter是接收客户端传过来的值,getAttribute方法是取到之前调用setAttribute方法保存到这个对象里面的值.

 */
@WebServlet("/ApplicationTest")
public class ApplicationTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext application = request.getServletContext();
		Object num = application.getAttribute("num");
		if(num == null){
			application.setAttribute("num", 1);
		}else{
			int n = (int) num;
			application.setAttribute("num", n+1);
		}
		request.getRequestDispatcher("test.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
