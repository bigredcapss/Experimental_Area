package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @WebFilter("/LoginFilter")标记的是对什么资源进行过滤，相当于web.xml中的
 *   <filter>
    <filter-name>login</filter-name>
    <filter-class>filter.LoginFilter</filter-class>
  </filter>
  <filter-mapping>
    <filter-name>login</filter-name>
    <url-pattern>/jsp/*</url-pattern>
  </filter-mapping>

 */
@WebFilter("/jsp/*")
public class LoginFilter implements Filter {

    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//用户登陆情况下才可以查看用户信息，
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("user");
		//如果用户信息为空，则跳转到登陆页面 
		if(obj == null){
			req.getRequestDispatcher("/login.jsp").forward(request, response);
		}else{
			chain.doFilter(req, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
