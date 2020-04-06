package annotion;

import bean.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @Scope注解:Controller默认情况下和servlet一样也是单例,但是spring提供了一个@Scope注解可以让Controller对象变为非单例,只需在Controller类上面加入@Scope("prototype")即可;
 * @author WE
 *
 */
@Controller
//@Scope("prototype")
public class SessionController {
	
	@RequestMapping("/getsess")
	public String sess(HttpSession session){
		User user = (User) session.getAttribute("u");
		System.out.println(user);
		return "hello";
	}
	/**
	 * @Value:用于将一个SpEL表达式结果映射到到功能处理方法的参数上
	 * @param name
	 * @return
	 */
	@RequestMapping("/getvalue")
	public String value(@Value("test") String name){
		System.out.println(name);
		return "hello";
	}
	@RequestMapping("/getvalue2")
	public String value2(@Value("#{user.getName()}") String name){
		System.out.println(name);
		return "hello";
	}
	/**
	 * @DateTimeFormat(pattern="yyyy-MM-dd")：将字符串转换为时间类型
	 * @param user
	 * @return
	 */
	@RequestMapping("/type1")
	public String type1(User user){
		System.out.println(user+"~~~~~~~~");
		return "hello";
	}
	/**
	 * @InitBinder注解:可以解决类型转换的问题
	 * @param binder
	 */
	@InitBinder
	public void type2(WebDataBinder binder){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		/**
		 * 注册用户自定义的类型转换器
		 * 第一个参数表示需要转换的类型；第二个参数表示自定义类型编辑器，true表示表单提交的时间数据允许为空
		 */
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));  
	}
	/**
	 * @RequestBody注解:可以接收客户端ajax请求中的json数据并且转换为对象,但是只能接收post请求中的数据,因为post请求的数据在请求体中(request-body).
	 * 这里仅仅获取请求的体部
	 * @param body
	 * @return
	 */
	@RequestMapping("/requestbody")
	public String requestBody(@RequestBody String body){
		System.out.println(body);
		return "hello";
	}
	/**
	 * @ResponseBody:将返回的值添加到响应的体部，写回给浏览器；对象不能直接写到响应体中，必须转换为字符串才可以
	 * @return
	 */
	@RequestMapping("/responsebody")
	public @ResponseBody String responseBody(){
	    User user = new User();
	    user.setId(1);
	    user.setName("ii");
	    user.setAge(12);
		return user.toString();
	}
	
	
	
	
	

}
