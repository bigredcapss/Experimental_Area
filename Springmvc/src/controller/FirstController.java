package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
/**
 * 控制器：处理浏览器请求，回写ModelAndView对象
 * @author WE
 *
 */
public class FirstController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {

		String name = req.getParameter("name");
		System.out.println("name:"+name);
		ModelAndView model = new ModelAndView();
		//设置逻辑视图hello
		model.setViewName("hello");
		//直接设置物理视图
//		model.setViewName("/WEB-INF/jsp/hello.jsp");
		
		//设置内容
		model.addObject("name", name);
		return model;
	}

}
