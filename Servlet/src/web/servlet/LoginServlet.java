package web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 浏览器地址栏的地址不变，返回一个页面资源或其它Servlet类执行的结果，这属于服务器内部跳转
 */
@WebServlet("/loginSer")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public LoginServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    //获取跳转器，其中的参数指向资源名称(WebContent下的静态资源和Servlet类);这里参数中的斜杠代表项目名称，基于Tomcat服务器去看这个路径
	    RequestDispatcher rd = request.getRequestDispatcher("/test.html");
	    //这里跳转到另一个页面，如果跳转到另一个Servlet类，则需要执行另一个Servlet类的service方法，这时会把request,response对象传给另一个service方法，传的时候不在基于Http协议，而是服务器内部跳转
	    rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
