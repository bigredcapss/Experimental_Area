package web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 向浏览器写入cookie
 * 	Cookie保存服务器端的信息（用户名，密码）
	cookie是以文件的方式呈现在浏览器中
	同时在tomcat中cookie是对象的方式呈现

	注意：cookie是response带给浏览器的
	浏览器接受的站点是有限的，每一个站点
	可以写入的cookie也是有限的（eg:
	火狐每个站点最多维护180个cookie）
	每一个cookie的值最多4096b（4kb）

	服务器写回cookie本地存在key，覆盖值

	如果浏览器cookie维护到达最大值，服务器在次
	写回cookie对象，浏览器移除最早写入的cookie


	浏览器禁用cookie，浏览器没办法Jsessionid值

 */
@WebServlet("/CookieTest")
public class CookieTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//注：给浏览器写cookie,cookie不能带中文
		Cookie cookie1 = new Cookie("name", "tom");
		//设置过多久浏览器删除该cookie
		cookie1.setMaxAge(60*60*24);
		response.addCookie(cookie1);
		Cookie cookie2 = new Cookie("password","112");
		cookie2.setMaxAge(60*60*24);
		response.addCookie(cookie2);
		request.getRequestDispatcher("test.jsp").forward(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
