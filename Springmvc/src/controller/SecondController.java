package controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * Controller处理器可以不返回ModelAndView对象；直接返回null,视图解析器就不会解析；
 * @author WE
 *
 */
public class SecondController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
/* PrintWriter pw = res.getWriter();
		pw.println("test....ok");
		pw.flush();
*/
		req.getRequestDispatcher("/WEB-INF/jsp/hello.jsp").forward(req, res);//这里只能进行服务器内部跳转,而不能进行重定向
		return null;//返回null,前端控制器不会调用视图解析器进行处理
	}

}
