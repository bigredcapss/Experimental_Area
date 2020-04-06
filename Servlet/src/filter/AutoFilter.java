package filter;

import bean.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 */
@WebFilter("/*")
public class AutoFilter implements Filter {
	public void destroy() {
		// TODO Auto-generated method stub
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		Cookie[] cookies = req.getCookies();
		Map<String,String> map = new HashMap<>();
		HttpSession session = req.getSession();
		if(cookies!=null){
			//遍历的时候如何拿到用户名和密码，也有可能用户自始至终都没登陆过，因此这里不管拿没拿到，想到用Map集合先保存一下
			for(Cookie cookie:cookies){
				map.put(cookie.getName(), cookie.getValue());
			}
			if(map.containsKey("name")&&map.containsKey("password")){
				//name-->Mysql-->User
				String name = map.get("name");
				String password = map.get("password");
				if(name.equals("tom")){
					if(password.equals(123)){
						User user = new User(name,password);
						session.setAttribute("user", user);
						//URI请求资源唯一标识符:csbn
						//URL不一定指向一个明确的资源
						String str = req.getRequestURI();
						System.out.println(str+"&&&&&&&&&&&&&&&&&");
						//字符串截取
						String re_path = str.substring(str.indexOf("/", 2),str.length());
						System.out.println(re_path+"****************");
						if(re_path.equals("/login.jsp")){
							req.getRequestDispatcher("/index.jsp").forward(request, response);
						}else{
							chain.doFilter(request, response);
						}
					}else{
						chain.doFilter(request, response);
					}
				}else{
					chain.doFilter(request, response);
				}
			}else{
				chain.doFilter(request, response);
			}
		}else{
			chain.doFilter(request, response);
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
