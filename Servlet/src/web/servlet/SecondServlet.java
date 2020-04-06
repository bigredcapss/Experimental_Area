package web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
/**
 * 第二种构建servlet的方式
 * GenericServlet实现了Servlet接口，只有service方法没有实现
 * @author WE
 *
 */
public class SecondServlet extends GenericServlet
{

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = res.getWriter();
		pw.write("<html>");
		pw.write("<head>");
		pw.write("<title>test</title>");
		pw.write("</head>");
		pw.write("<body>");
		pw.write("success<br>");
		pw.write("<input type='text' name=name>");
		pw.write("</body>");
		pw.write("</html>");
	}

}
