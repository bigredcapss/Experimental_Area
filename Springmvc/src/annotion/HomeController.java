package annotion;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Controller:表示控制器
 * @author WE
 *
 */
@Controller
public class HomeController {
	/**
	 * @RequestMapping("/home"):对应的就是资源名称
	 * @return
	 */
	@RequestMapping("/home")
	public ModelAndView test(){
		ModelAndView model = new ModelAndView();
		System.out.println("SpringMVC....annotion");
		model.setViewName("hello");
		return model;
	}
	/**
	 * 返回字符串，方法的形参可以随便写，也可以不写；Spring配置文件中配置了视图解析器，则返回逻辑视图名；
	 * Spring配置文件中没有配置视图解析器，则返回对应物理视图
	 * @return
	 */
	@RequestMapping("/home1")
	public String test1(){
		return "hello";
	}
	/**
	 * 传一个Model类型的参数
	 * @param model
	 * @return
	 */
	@RequestMapping("/home2")
	public String tes2(Model model){
		//向model中添加数据
		model.addAttribute("name", "jake");
		return "hello";
	}
	/**
	 * 传一个HttpServletResponse类型的对象
	 * @param res
	 */
	@RequestMapping("/home3")
	public void test3(HttpServletResponse res){
		try {
			PrintWriter pw = res.getWriter();
			pw.println("test3...ok");
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
