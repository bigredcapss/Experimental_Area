package intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 拦截器单例，线程不安全，当多个请求，同时访问相同的控制器中的方法时，该方法只有一个拦截器
 * @author WE
 *
 */
public class TimeInterceptor extends HandlerInterceptorAdapter
{
	/**
	 * ThreadLocal:当多线程环境操作相同的变量的时候，ThreadLocal就是和每一个线程绑定的变量，能保证线程的安全；注意：内部只能存储一个值
	 */
	private ThreadLocal<Long> timer = new ThreadLocal<>();
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		long start_date = System.currentTimeMillis();
		timer.set(start_date);
		return true;
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		long end_date = System.currentTimeMillis();
		long start_date = timer.get();
		System.out.println("总时间为："+(end_date-start_date));
	}
	

}
