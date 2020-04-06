package intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//通过request获取请求的资源名称
		System.out.println(request.getServletPath()+"");
		//通过request获取项目的名字
		System.out.println(request.getContextPath()+"");
		
		
		if(request.getServletPath().startsWith("/login")){
			return true;
		}
		if(request.getSession().getAttribute("user")!=null){
			return true;
		}
		/**
		 * 直接进入登陆页面
		 */
		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
		//重定向方式
//		response.sendRedirect(request.getContextPath()+"/login");
		
		return super.preHandle(request, response, handler);
	}

}
