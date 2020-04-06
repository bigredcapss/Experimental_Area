package adapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface MyController {
	ModelAndView handle_test(HttpServletRequest req,HttpServletResponse res);
}
