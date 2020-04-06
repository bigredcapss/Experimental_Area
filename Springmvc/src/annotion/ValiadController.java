package annotion;

import bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
//@SessionAttributes("user")
public class ValiadController {
	
/*	@RequestMapping("/valiad")
	public String valiad(Model model){
		model.addAttribute("user", new User());
		return "valiad";
	}
	
	@RequestMapping("/valiad1")
	public String valiad1(User u){
		System.out.println(u+"******");
		return "valiad";
	}*/
	
	@ModelAttribute("user")
	public User getUser(){
		return new User();
	}
	
	@RequestMapping(value="/valiad3",method=RequestMethod.GET)
	public String valiad4(){
		return "valiad";
	}
	/**
	 * @Valid:表示当前该对象需要验证；验证不合法的信息存储在BindingResult中；带给页面两个信息：1.封装的user对象，2.不合法的信息br
	 * @param u
	 * @return
	 */
	@RequestMapping(value="/valiad3",method=RequestMethod.POST)
	public String valiad3(@Valid User u,BindingResult br){
		System.out.println(u);
		//验证是否有不合法信息
		if(br.hasErrors()){
			//获取所有的不合法信息
			List<ObjectError> errors = br.getAllErrors();
			for(ObjectError error:errors){
				System.out.println(error.getDefaultMessage());
			}
			return "valiad";
		}
		return "index";
	}

}
