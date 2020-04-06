package web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;

/**
 *
 */
@WebServlet("/LoginServlet")
public class LoginServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取前台传过来的参数
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String auto = request.getParameter("auto");
		System.out.println(auto);
		//获取session对象
		HttpSession session = request.getSession();
		//name password--->Mysql----->User
		if(name.equals("tom")){
			if(password.equals("123")){
				//注意这里由于其它地方也可能会用到User对象，故这里将User对象保存到session中
				User u = new User(name,password);
				session.setAttribute("user", u);
				//判定用户有没有自动登录,如果用户选择了自动登陆，将用户名，密码写入到cookie中
				if(auto.equals("1")){
					Cookie cookie1 = new Cookie("name",name);
					cookie1.setMaxAge(60*60*24);
					Cookie cookie2 = new Cookie("password",password);
					cookie2.setMaxAge(60*60*24);
					response.addCookie(cookie1);
					response.addCookie(cookie2);
				}
				request.getRequestDispatcher("index.jsp").forward(request, response);

			}else{
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}else{
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
