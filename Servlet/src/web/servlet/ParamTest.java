package web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Address;
import bean.User;

/**
 * 
 */
@WebServlet("/ParamTest")
public class ParamTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<User> list = new ArrayList<>();
		User user1 = new User("tom","111");
		Address addres1 =new Address("河南省","南阳市","唐河县");
		user1.setAddre(addres1);
		
		User user2 = new User("tom","111");
		Address addres2 =new Address("河南省","南阳市","唐河县");
		user2.setAddre(addres2);
		
		User user3 = new User("tom","111");
		Address addres3 =new Address("河南省","南阳市","唐河县");
		user3.setAddre(addres3);
		
		list.add(user1);
		list.add(user2);
		list.add(user3);
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("/EL1.jsp").forward(request, response);

		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
