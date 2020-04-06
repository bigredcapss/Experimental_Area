package web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * a.服务器内部跳转
	   1.跳转到一个页面
	   2.跳转到另外一个servlet

	   本质是：浏览器发出请求调用服务器servlet的service方法，
	           该方法需要调用其他servlet的service方法或需要复制页面内容给浏览器
	//获得一个跳转对象(服务器内部跳转)
	RequestDispatcher rd = req.getRequestDispatcher(page);
	//跳转
	rd.forward(req,resp);

	特点:
	1.不管servlet服务器内部跳转多少次,浏览器地址栏中显示的地址都是第一次访问到的servlet的地址
	2.服务器内部跳转其实就是把request和response对象进行转发,转发到另外一个服务器内部的一个资源中,
	  如果转发的对象是一个页面,那么就把这个页面返回给浏览器,如果转发的对象是另外一个servlet,
	  那么会调用到这个servlet里面的service方法,然后把之前那个request和response当做传送传进来.
	3.在服务器内部跳转的过程中,每一个环节中所用到的request和response对象都是同一个request和response对象.

	注意：forward跳转如果跳转多个servlet，前面的servlet中向response中写的内容会被后面的servlet中的内容覆盖掉

 */
@WebServlet("/TestServlet1")
public class TestServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TestServlet1() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String name = request.getParameter("name");
		System.out.println(name+".....TestServlet1");
	    PrintWriter pw = response.getWriter();
	    //注意这里不用调用flush和close方法，因为在跳转多个Servlet类时，Tomcat会在最后一个Servlet类中调用flush和close方法
	    pw.write("byebye...");
		//跳转到另一个Servlet类中去
		//request.getRequestDispatcher("/TestServlet2").forward(request, response);
		//如果多个Servlet类中都向浏览器中写内容，而且要让写的内容都显示出来，则调用include方法;
	    //include负责跳转，不管哪一个Servlet向浏览器中写内容，使用include是追加效果
		request.getRequestDispatcher("/TestServlet2").include(request, response);

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
