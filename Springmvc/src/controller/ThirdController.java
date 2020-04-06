package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 * 实现特定的功能用特定的类；
 * 同一个浏览器发出多个请求，请求相同的资源的时候(会话级别的)，对访问的资源加锁；浏览器每发出一次请求，服务器都会为该请求启动一个线程去处理(多次请求用同一个线程，也可能多次请求用不同的线程 )
 * 
 * @author WE
 *
 */
public class ThirdController extends AbstractController{

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView model = new ModelAndView();
		//设置逻辑视图
		model.setViewName("hello");
		return model;
	}

}
