package adapter;

import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 自定义适配器
 * @author WE
 *
 */
public class DefineAdapter implements HandlerAdapter{

	@Override
	public boolean supports(Object handler) {
		// TODO Auto-generated method stub
		return (handler instanceof MyController);
	}

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		return ((MyController) handler).handle_test(request, response);
	}

	@Override
	public long getLastModified(HttpServletRequest request, Object handler) {
		// TODO Auto-generated method stub
		return 0;
	}

}
