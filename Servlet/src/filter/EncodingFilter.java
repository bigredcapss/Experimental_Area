package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 	过滤器（filter）
	拦截资源，对资源进行校验，符合规范
	放行（原来请求什么资源接着请求该资源），
	不符合规范特殊处理
	过滤器所处的位置：servlet执行之前
 * @author WE
 *
 */
public class EncodingFilter implements Filter{

	//过滤器销毁时调用的方法
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	//doFilter对资源进行校验的方法,第三个参数：放行对象，即符合规范，放行
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		//System.out.println("before..........");
		//放行(原来请求什么资源接着请求什么资源)
		chain.doFilter(req, res);
		//System.out.println("after..............");
		
	}

	//初始化方法
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
