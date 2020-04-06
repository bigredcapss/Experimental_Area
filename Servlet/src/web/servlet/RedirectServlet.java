package web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 重定向测试
 */
@WebServlet("/RedirectServlet")
public class RedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public RedirectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//资源参数中没有斜杠，拼接在浏览器地址栏最后一个斜杠的后面
		response.sendRedirect("login.html");
		
		//加斜杠，资源则直接拼接在端口号后面，因此需要家项目名
		//response.sendRedirect("/login.html");
		//response.sendRedirect("/ServletStudy/login.html");
		//response.sendRedirect("http://127.0.0.1:8888/ServletStudy/login.html");//写绝对路径也可以
		//response.sendRedirect("../login.html");
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
