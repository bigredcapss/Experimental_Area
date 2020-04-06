package web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;

/**
 * 后台往前台传值，前台用EL表达式取值
 */
@WebServlet("/ParamServlet")
public class ParamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("name", "tom");
		HttpSession session = request.getSession();
		session.setAttribute("age", 33);
		ServletContext application = request.getServletContext();
		application.setAttribute("gender", "男");
		
		String[] str={"test1","test2","test3"};
		request.setAttribute("str", str);
		
		User[] users ={
				new User("marry","1111"),
				new User("jane","2222"),
				new User("KK","3333")
		};
		request.setAttribute("users", users);
		
		List<User> list = new ArrayList<>();
		list.add(new User("HH","5555"));
		list.add(new User("JJ","6666"));
		list.add(new User("MM","7777"));
		request.setAttribute("list", list);
		
		Map<String,User> map = new HashMap<>();
		map.put("test1", new User("aa","1"));
		map.put("test2", new User("aa","1"));
		map.put("test3", new User("aa","1"));
		map.put("test4", new User("aa","1"));
		request.setAttribute("map", map);
		
		request.getRequestDispatcher("el.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
