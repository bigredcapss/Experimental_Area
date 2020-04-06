package adapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
/**
 * 实现自定义的控制器接口
 * @author WE
 *
 */
public class FourController implements MyController{

	@Override
	public ModelAndView handle_test(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		ModelAndView model = new ModelAndView();
		model.setViewName("hello");
		model.addObject("name", "four...test");
		return model;
	}

}
