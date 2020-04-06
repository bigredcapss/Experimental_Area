package web.servlet;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 从前台往后台提交数据除了form表单,还有a标签(href指向的也是一个资源名称)等；
 * 其中form表单往后台提交数据时，是浏览器自动拼接，而a表签这里，需要我们手动拼接
 */
//@WebServlet("/html/registerSer")
@WebServlet("/html/registerSer")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public RegisterServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取浏览器传的参数，getParameter方法获得前台参数的名字，后台接收的参数都是String类型的
//		String name = request.getParameter("name");
//		String password = request.getParameter("password");
//		System.out.println(name +"-----"+ password);
		
		//如果前台传入的参数等号前面的名字相同(name=&name=),后台获取的得到的是数组；
		//注意：getParameterValues,getParameter取值时，参数指定的key不存在，后台取到的是null值，key存在取到的是空的的字符串数组
		String[] hobys =request.getParameterValues("hoby");
		System.out.println(Arrays.toString(hobys));
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
