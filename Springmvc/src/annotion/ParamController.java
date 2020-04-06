package annotion;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import bean.User;

/**
 * 数据绑定：(1)功能处理方法支持的参数类型
 * @author WE
 * @SessionAttributes:如果当前Controller中向ModelAttribute中存入了对象，对象会同步到Session中保存，Session是会话级别的，
 * 后期在ModelAttribute中使用该对象，直接从session中取;types只限定当前控制器的存储类型，其它控制器可以向session中存储任意的数据类型；
 * 这里types也可以放多个值，e.g:types={"User.class","Teacher.class"}
 */
@Controller
//@SessionAttributes(value="u",types=User.class)
public class ParamController {
	
	/**
	 * HttpServletRequest/ServletRequest,HttpServletResponse/ServletResponse
	 * 参数都可以在功能处理方法中直接声明并且没有指定顺序,spring会自动注入的;
	 * SpringMVC框架会自动帮助我们把相应的Servlet请求/响应作为参数传递过来。
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/param")
	public String paramTest1(HttpServletRequest req,HttpServletResponse res){
		System.out.println(req+"===="+res);
		return "hello";
	}
	/**
	 * OutputStream定义为参数的时候，方法不能有返回值；
	 * InputStrema操作请求的体部，拿出Http请求体的内容
	 * OutputStream向请求的体部写内容
	 * @param is
	 * @param os
	 * @return
	 */
	@RequestMapping("/param1")
	public void paramTest2(InputStream is,OutputStream os){
		System.out.println(is+"+++++++"+os);
		byte[] b = new byte[1024];
		try {
			//读请求体中的内容
			is.read(b);
			System.out.println(new String(b));
			//往请求体中写内容
			String str = "hello";
			os.write(str.getBytes());
			os.flush();
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * WebRequest、NativeWebRequest
	 * 
	 * @param is
	 * @param os
	 * @return
	 */
	@RequestMapping("/param2")
	public String paramTest2(WebRequest is,NativeWebRequest os){
		String name = is.getParameter("name");
		String age = os.getParameter("age");
		System.out.println(name+"----"+age);
		//设置值在request容器中,WebRequest.SCOPE_REQUEST将值存在某一个容器中
		is.setAttribute("name", "persons", WebRequest.SCOPE_REQUEST);
		//将值存在session对象中
		is.setAttribute("name", "zzuli", WebRequest.SCOPE_SESSION);
		//取值
		System.out.println(is.getAttribute("name", WebRequest.SCOPE_REQUEST));
		System.out.println(is.getAttribute("name", WebRequest.SCOPE_SESSION));
		
		System.out.println("*******************************************************");
		HttpServletRequest request =  os.getNativeRequest(HttpServletRequest.class);
		System.out.println(request);
		return "hello";
	}
	/**
	 * 注意:session访问不是线程安全的,如果需要线程安全,需要设置AnnotationMethodHandlerAdapter或RequestMappingHandlerAdapter的synchronizeOnSession属性为true
	 * ,即可线程安全的访问session。
	 * @param session
	 * @return
	 */
	@RequestMapping("/param3")
	public String paramTest3(HttpSession session){
		System.out.println(session);
		return "hello";
	}
	/**
	 * 可以将表单中的属性自动装载到参数中的对象中；
	 * 参数中可以获取对象的同时，跳转到的视图同样可以获取该数据
	 * 注意：页面取值用EL表达式，key是参数中类型名字，首字母小写
	 * @param user
	 * @return
	 */
	@RequestMapping("/param4")
	public String paramTest4(User user){
		System.out.println(user);
		return "hello";
	}
	/**
	 * model往页面传数据是Request级别的；
	 * Model,Map<Object,Object>,ModelMap类型是同样的效果
	 * @param m
	 * @return
	 */
	@RequestMapping("/param5")
	public String paramTest4(Model m){
		m.addAttribute("name", "jake");
		return "hello";
	}
	/**
	 * HttpEntity把Http请求内容封装成一个对象
	 * @param en
	 * @return
	 */
	@RequestMapping("/param6")
	public String paramTest5(HttpEntity<String> en){
		System.out.println(en.getHeaders());
		String body = en.getBody();
		System.out.println(body);
		return "hello";
	}
	/**
	 * 基于响应的协议返回实体对象
	 * @param en
	 * @return
	 */
	@RequestMapping("/param7")
	public ResponseEntity<String> paramTest6(){
		//创建响应头对象
		HttpHeaders headers = new HttpHeaders();
		//创建MediaType对象；等价于headers.set("Content-Type", "text/html;charset=UTF-8");设置响应类型，告诉浏览器传入的文本类型，及用什么编码进行处理
		MediaType mt = new MediaType("text","html",Charset.forName("UTF-8")); 
		//设置ContentType
		headers.setContentType(mt);  
		//准备好响应体
		String content = new String("hello world");
		//根据响应内容/响应头信息/响应状态码创建响应对象
		ResponseEntity<String> re = new ResponseEntity<String>(content,headers,HttpStatus.OK);
		//返回ResponseEntity对象
		return re;
	}
	/**
	 * SessionStatus中的setComplete()方法可以用来清除使用@SessionAttributes注解放到session中的数据
	 * @param status
	 */
	@RequestMapping("/param8")
	public void paramTest7(SessionStatus status){
		System.out.println(status);
	}
	
	/**
	 * 这里仍然带不回数据
	 * @param model
	 * @param res
	 * @return
	 */
/*	@RequestMapping("/param9")
	public String paramTest8(Model model,HttpServletResponse res){
		model.addAttribute("msg", "test................");
		try {
			res.sendRedirect("paramindex");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}*/
	/**
	 * RedirectAttributes可以帮我们解决这个问题,既要使用客户端重定向,又要把一些有用的数据保留到下一次重定向的请求之中
	 * @param ra
	 * @return
	 */
	@RequestMapping("/param9")
	public String paramTest8(RedirectAttributes ra){
		ra.addFlashAttribute("msg", "第二次请求带回值");
		return "redirect:/paramindex";
	}
	@RequestMapping("paramindex")
	public String index(){
		return "index";
	}
	/**
	 * 默认参数变量名和资源名称中的参数一致，可以取到值；如果资源名称和参数变量中的参数名不一致时，使用@RequestParam注解对方法中的参数进行限定，表示将资源名称中的参数赋给当前方法中的参数
	 * defaultValue:默认值,表示如果请求中对应参数时则采用此默认值,默认值可以是spring中的SpEL 表达式
	 * @param name
	 * @return
	 */
	@RequestMapping("/param10")
	public String paramTest9(@RequestParam(value="username",defaultValue="tom") String name){
		System.out.println(name+"=====");
		return "hello";
	}
	
	@RequestMapping("/param11")
	public String paramTest10(@RequestParam(value="name",defaultValue="#{systemProperties['java.vm.version']}") String name){
		System.out.println(name+"=====");
		return "hello";
	}
	/**
	 * SpEl表达式基于Spring容器中用bean表签的名字取对象，在基于对象取方法#{key.att},att如果是字段，拼接get,首字母大写调用该方法，取到的值和参数类型不一致的时候，自动转换为参数类型
	 * att也可以是自定义的方法(有参，无参)
	 * @param name
	 * @return
	 */
	@RequestMapping("/param12")
	public String paramTest11(@RequestParam(value="name",defaultValue="#{user.id}") String name){
		System.out.println(name+"=====");
		return "hello";
	}
	
	@RequestMapping("/param13")
	public String paramTest12(@RequestParam(value="name",defaultValue="#{user.sayHello()}") String name){
		System.out.println(name+"=====");
		return "hello";
	}
	
	@RequestMapping("/param14")
	public String paramTest14(@RequestParam(value="name",defaultValue="#{user.sayHello('Tom')}") String name){
		System.out.println(name+"=====");
		return "hello";
	}
	/**
	 * 表达式(?.)可以确保在sayHello()返回不为空的情况下调用toUpperCase()方法,如果返回空那么不继续调用后面的方法
	 * @param name
	 * @return
	 */
	@RequestMapping("/param15")
	public String paramTest15(@RequestParam(value="name",defaultValue="#{user.sayHello('Tom')?.toUpperCase()}") String name){
		System.out.println(name+"=====");
		return "hello";
	}
	/**
	 * 调用某个静态方法T(全限定类名).方法名
	 * @param name
	 * @return
	 */
	@RequestMapping("/param16")
	public String paramTest16(@RequestParam(value="name",defaultValue="#{T(bean.User).sayYou()}") String name){
		System.out.println(name+"=====");
		return "hello";
	}
	/**
	 * 取Cookie时一定要指定Cookie的key值
	 * @param userid
	 * @return
	 */
	@RequestMapping("/param17")
	public String paramTest17(@CookieValue(value="JSESSIONID") String userid){
		System.out.println(userid);
		return "hello";
	}
	/**
	 * defaultValue和之前的作用相同
	 * @param userid
	 * @return
	 */
	@RequestMapping("/param18")
	public String paramTest18(@CookieValue(value="my_test",defaultValue="#{user.getName()}") String userid){
		System.out.println(userid);
		return "hello";
	}
	/**
	 * 取Cookie中的值时，如果方法中的参数是字符串，直接将值赋给字符串，如果方法中的参数是Cookie对象，把当前取值的健和取到的值封装到Cookie中去
	 * @param cookie
	 * @return
	 */
	@RequestMapping("/param19")
	public String paramTest19(@CookieValue(value="JSESSIONID") Cookie cookie){
		System.out.println(cookie);
		System.out.println(cookie.getName()+"-----"+cookie.getValue());
		return "hello";
	}
	/**
	 * 基于key取请求头的某个值
	 * @param head
	 * @return
	 */
	@RequestMapping("/param20")
	public String paramTest20(@RequestHeader("Accept") String head){
		System.out.println(head);
		return "hello";
	}
	/**
	 * RequestHeader基于key取值，赋给的参数如果是字符串数组，基于key取到的值在基于逗号拆开
	 * @param head
	 * @return
	 */
	@RequestMapping("/param21")
	public String paramTest21(@RequestHeader("Accept") String[] head){
		System.out.println(head.length);
		System.out.println(head);
		return "hello";
	}
	/**
	 * @ModelAttribute("u")基于封装的请求参数类型起别名，在返回的视图中，基于别名取值；
	 * 默认是，在返回的视图中，基于参数的类型取值
	 * @param user
	 * @return
	 */
	@RequestMapping("/param22")
	public String paramTest22(@ModelAttribute("u") User user){
		System.out.println(user+"-------");
		return "hello";
	}
	/**
	 * 构建user对象给paramTest22方法传进去，但表单中填写的信息会覆盖该对象设置的值
	 * @return
	 */
	@ModelAttribute("u")
	public User getUser(){
		System.out.println("getUser.............................................");
		User user = new User();
		user.setAge(46);
		user.setName("kkk");
		return user;
	}
	/**
	 * 返回值前加@ModelAttribute注解，表示把数据带给视图，视图是请求资源名称;
	 * 注意类上的注解@@RequestMapping,如果类中有该注解，则相应的应更改资源文件的路径；
	 * 在前台视图中，用EL表达式取值时要基于@ModelAttribute("us")us取值
	 * @return
	 */
/*	@RequestMapping("/index")
	public @ModelAttribute("us") User go(){
		User user = new User();
		user.setId(13);
		user.setName("bbb");
		user.setAge(120);
		return user;
	}*/
	/**
	 * 对于集合类型(Collection接口的实现者们,包括数组),生成的模型对象属性名为"简单类名(首字母小写)"+"List",
	 * 如List<String>生成的模型对象属性名"stringList",List<User>生成的模型对象属性名为"userList"
	 * @return
	 */
	@RequestMapping("/index")
	public @ModelAttribute List<User> go1(){
		User user = new User();
		user.setId(13);
		user.setName("bbb");
		user.setAge(120);
		User user1 = new User();
		user1.setId(14);
		user1.setName("mmm");
		user1.setAge(220);
		List<User> list = new ArrayList<>();
		list.add(user1);
		list.add(user);
		return list;
	}
	/**
	 * 测试@SessionAttribute注解，该方法是清空session
	 * @param status
	 * @return
	 */
	@RequestMapping("/clearsess")
	public String clearSess(SessionStatus status){
		//清空当前控制器上声明存储的数据
		status.setComplete();
		return "hello";
	}
	@RequestMapping("/param23")
	public String paramTest23(@ModelAttribute("use") User user){
		System.out.println(user+"-------");
		return "hello";
	}
	@ModelAttribute("use")
	public User getUser1(){
		System.out.println("getUser1//////////");
		return new User();
	}
	
	

	
	

}
