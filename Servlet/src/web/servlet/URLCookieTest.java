package web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 	浏览器禁用cookie，浏览器没办法保存Jsessionid值
 * e.g:每次访问网站，都要重新登陆
	解决方法：重写URL技术（每次发送请求都把cookie带上）
	本质:请求资源后面追加Jsessionid
	http://127.0.0.1:8888/jd1812_web/sess;jsessionid=94ECB2671180E6C9FD2C7832646D7069

 */
@WebServlet("/URLCookieTest")
public class URLCookieTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//这里因为浏览器禁用了cookie,没办法保存服务器端的信息，所以要先获取session对象
		HttpSession session = request.getSession();
		System.out.println("session:"+session.getId());
		//手动拼接
		String path = "SessionTest;jsessionid="+session.getId();
		//encodeURL自动在资源名称后面拼接jsessionid
		//注意：在encodeURL前面要先获取session对象
//		String path = response.encodeURL("SessionTest");
		System.out.println(path);
//		request.setAttribute("id", session.getId());
		//重定向
		response.sendRedirect(path);
		//跳到页面
//		request.getRequestDispatcher("/test.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
