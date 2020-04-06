package annotion;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ForwardController {
	/**
	 * 1.因为在Controller中的功能处理方法上可以获得到request和response,所以可以像之前servlet中一样,进行服务器内部跳转和客户端重定向
	 * @param req
	 * @param res
	 */
	@RequestMapping("/forw")
	public void forward(HttpServletRequest req,HttpServletResponse res){
		try {
			req.getRequestDispatcher("/WEB-INF/jsp/hello.jsp").forward(req, res);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("/forw1")
	public void forward1(HttpServletRequest req,HttpServletResponse res){
		try {
			//req.getRequestDispatcher("/ind").forward(req, res);
			res.sendRedirect("ind");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	@RequestMapping("/ind")
	public String Ind(){
		return "index";
	}
	
	@RequestMapping("/forw2")
	public String forward2(){
//		return "index";
		//请求处理器(处理请求和响应的方法)
//		return "forward:forw1";
		//服务器内部跳转到页面，需要写完整的物理视图的名字
//		return "forward:/WEB-INF/jsp/index.jsp";
		//重定向到其它处理器
		return "redirect:/forw1";
		//重定向到一个新的页面，该页面需要放在WebContent路径下，否则访问不到而只能通过服务器内部调转来访问
//		return "redirect:/index.jsp";
	}
	
	@RequestMapping("/forw3")
	public ModelAndView forward3(){
		//参数表示逻辑视图名
//		ModelAndView model = new ModelAndView("hello");
		//服务器内部跳转到其它处理器上
//		ModelAndView model = new ModelAndView("forward:/forw1");
		//服务器内部跳转到一个页面，参数直接写物理视图
//		ModelAndView model = new ModelAndView("forward:/WEB-INF/jsp/hello.jsp");
		//重定向到其它的处理器
		ModelAndView model = new ModelAndView("redirect:forw1");
		//重定向到其它页面
//		ModelAndView model = new ModelAndView("redirect:index.jsp");
		return model;
	}
	
	

}
