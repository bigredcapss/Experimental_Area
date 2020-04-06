package annotion;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * 在类中写@RequestMapping("/user")，可以理解为该注解标记了某一模块；在浏览器中请求时应为http:localhost:8888/SpringMVC/user/act;即类中的@RequestMapping注解是基于功能区分用的
 * @author WE
 *
 */
@Controller
@RequestMapping("/user")
public class ActionController {
	/**
	 * 多个资源名字对应一个方法
	 * @return
	 */
	@RequestMapping(value={"/act","test","action"})
	public String test(){
		return "hello";
	}
	/**
	 * @PathVariable:将restful风格中的{}占位中的变量代表的值赋给参数;
	 * @param userid
	 * @return
	 */
	@RequestMapping("/user/{userid}")
	public String restFul(@PathVariable String userid){
		System.out.println(userid+"----------");
		return "hello";
	}
	/**
	 * 默认，将restful风格中的{}占位中的变量和方法参数中的表示的变量相同，若不同，在@PathVariable("userid")注解中指定名字
	 * @param username
	 * @return
	 */
	@RequestMapping("/user1/{userid}")
	public String restFul1(@PathVariable("userid") String username){
		System.out.println(username+"----------");
		return "hello";
	}
	/**
	 * 浏览器中输入的占位中的值，会自动转换为方法中参数的类型
	 * @param username
	 * @return
	 */
	@RequestMapping("/user2/{userid}")
	public String restFul2(@PathVariable("userid") long username){
		System.out.println(username+"----------");
		return "hello";
	}
	/**
	 * password没有占位，在浏览器中请求时，也必须写上，路径为：
	 * @param username
	 * @return
	 */
	@RequestMapping("/user3/{userid}/password")
	public String restFul3(@PathVariable("userid") long username){
		System.out.println(username+"----------");
		return "hello";
	}
	/**
	 * 模板和占位同时存在时，模板有优先权
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping("/user3/{userid}/{password}")
	public String restFul4(@PathVariable("userid") long username,@PathVariable String password){
		System.out.println(username+"----------"+password);
		return "hello";
	}
	/**
	 * Ant风格的url映射；*：表示资源名称
	 * @return
	 */
	@RequestMapping("/product/*")
	public String ant(){
		System.out.println("单个*");
		return "hello";
	}
	/**
	 * 两个*：**表示可以出现带斜杠的资源名称
	 * @RequestMapping("/product/？")：问号表示匹配单个字符
	 * @return
	 */
	@RequestMapping("/product/**")
	public String ant1(){
		System.out.println("test...ok");
		return "hello";
	}
	/**
	 * Ant风格和URI模板变量风格可混用
	 */
	@RequestMapping("/product**/{productid}")
	public String ant2(@PathVariable long productid){
		System.out.println("test---------ok");
		System.out.println(productid+"-------");
		return "hello";
	}
	/**
	 * 正则表达式风格的URL路径映射,格式为{变量名:正则表达式}
	 * @return
	 */
	@RequestMapping("/regex/{departid:\\d+}")
	public String regex(@PathVariable String departid){
		System.out.println("departmentid:"+departid);
		return "hello";
	}
	/**
	 * http:localhost:8888/SpringMVC/user/regex/123aa78
	 */
	@RequestMapping("/regex/{userid:^\\d{3}.*[1-9]{2}$}")
	public String regex1(@PathVariable String userid){
		System.out.println("userid:"+userid);
		return "hello";
	}
	/**
	 * 限制浏览器请求的方式,method限制提交的方式
	 * @param userid
	 * @param username
	 * @return
	 */
//	@RequestMapping("/test/{userid}")
	@RequestMapping(value="/test/{userid}",method={RequestMethod.POST,RequestMethod.GET})
	public String register(@PathVariable String userid,String username){
		System.out.println(username+"---userid:"+userid);
		return "hello";
	}
	/**
	 * 请求参数的限定：http://localhost:8888/SpringMVC/user/param/name
	 * @return
	 */
//	@RequestMapping(value="/param",params="name")
//	public String paraTest(){
//		System.out.println("param......test");
//		return "hello";
//	}
	/**
	 * !表示参数不能出现name属性：http://localhost:8888/SpringMVC/user/param
	 * @return
	 */
//	@RequestMapping(value="/param",params="!name")
//	public String paraTest1(){
//		System.out.println("param1......test");
//		return "hello";
//	}
	/**
	 * 参数的key和value必须要匹配，否则找不到方法：http://localhost:8888/SpringMVC/user/param?name=tom;这里运行时，上面两个必须要注释掉
	 * @return
	 */
//	@RequestMapping(value="/param",params="name=tom")
//	public String paraTest2(){
//		System.out.println("param2......test");
//		return "hello";
//	}
	/**
	 * 多个参数的限定，组合使用是"且"的关系:http://localhost:8888/SpringMVC/user/param?create&name=tom
	 * @return
	 */
	@RequestMapping(value="/param",params={"age","name=jake"})
	public String paraTest3(){
		System.out.println("param2......test");
		return "hello";
	}
	/**
	 * 请求头数据映射的限定
	 * @return
	 */
	@RequestMapping(value="/head",headers="Accept")
	public String headTest(){
		System.out.println("head......test");
		return "hello";
	}
	/*
	 * 请求头中必须有Accept=text/plain即可匹配该参数
	 */
	@RequestMapping(value="/head",headers="Accept=text/plain")
	public String headTest1(){
		System.out.println("head......test1");
		return "hello";
	}
	/**
	 * 浏览器必须发Content-Type:application/json格式的数据，才会匹配到该方法响应
	 */
	@RequestMapping(value="jsonhead",consumes="application/json")
	public void goTest(HttpServletRequest req){
		System.out.println("json.......test");
		//获取浏览器发送的数据的长度
		int length = req.getContentLength();
		//获取编码信息
		String nn =req.getCharacterEncoding();
		System.out.println(nn+"编码");
		//声明一个缓存，缓存的长度就是内容的长度
		byte[] b = new byte[length];
		try {
			//服务器接收浏览器发送的数据
			InputStream is = req.getInputStream();
			//读取缓存内容
			is.read(b);
			System.out.println(new String(b,Charset.forName(nn)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * produces和浏览器发送的Accept属性配对
	 */
	@RequestMapping(value="jsonhead",produces="application/json")
	public void headJson(HttpServletResponse res){
		//设置服务器响应的类型
		res.setContentType("application/json;charset=UTF-8");
		//响应内容：{"id":1,"name":"we","age":33}
		String str = "{\"id\":1,\"name\":\"we\",\"age\":33}";
		//设置写回内容的长度
		res.setContentLength(str.getBytes().length);
		//写回内容
		PrintWriter pw =null;
		try {
			pw = res.getWriter();
			pw.println(str);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
	
	

}
