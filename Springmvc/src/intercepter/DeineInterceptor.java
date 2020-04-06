package intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class DeineInterceptor implements HandlerInterceptor{
	/**
	 * 执行处理器之前执行的操作，相当于之前学的前置通知
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("before....DeineInterceptor");
		/**
		 * true:true表示继续流程(如调用下一个拦截器或处理器)
		 * false：表示流程中断(如登录检查失败),不会继续调用其他的拦截器或处理器,此时我们需要通过response来产生响应
		 */
		return true;
	}
	/**
	 * Controller处理器执行之后，视图渲染之前执行的操作
	 */
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle.......");
	}
	/**
	 * 视图解析器渲染视图之后的操作
	 */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception ex) throws Exception {
		System.out.println("afterCompletion.......");
	}

}
