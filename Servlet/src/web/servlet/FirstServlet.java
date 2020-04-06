package web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
/**
 * 第一种构建Servlet的方式
 * @author WE
 *
 */
public class FirstServlet implements Servlet
{

	//当前Servlet对象销毁时调用该方法
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	//获取Servlet的配置对象
	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	//获取Servlet对象的基本信息
	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	//初始化Servlet对象
	@Override
	public void init(ServletConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	//处理浏览器发送的请求，并回写资源，ServletRequest对象对Http请求进行封装，把各种各样的参数进行提取
	//ServletResponse对象给浏览器写内容，写的时候自动封装成http响应格式的数据，内部维护了字节流和字符流
	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//设置响应的文本类型
		arg1.setContentType("text/html;charset=UTF-8");
		//获取网络输出流
		PrintWriter pw = arg1.getWriter();
		pw.write("test ok");
		pw.flush();
		pw.close();
		
	}

}
