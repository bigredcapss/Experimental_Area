package web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 */
@WebServlet("/RequestTest")
public class RequestTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object num = request.getAttribute("num");
		if(num == null){
			request.setAttribute("num", 3);
		}else{
			int n = (int) request.getAttribute("num");
			request.setAttribute("num", n+1);
		}
		//request.getRequestDispatcher("/test.jsp").forward(request, response);
		//重定向时，加斜杠，资源拼接在端口号后面，因此要加项目名称
		response.sendRedirect("/ServletStudy/test.jsp");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
