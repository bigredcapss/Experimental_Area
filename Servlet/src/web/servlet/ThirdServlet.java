package web.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 构建Servlet的第三种方式
 * @author WE
 *
 */
public class ThirdServlet extends HttpServlet
{
	/**
	 * web中的请求只有两种GET|POST
	 * 当浏览器提交的请求是get方式，调用doGet方法；当浏览器提交的请求是post方式，调用doPost方法
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("进入了get方法");
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//设置响应浏览器的内容的类型
		resp.setContentType("image/jpeg");
		
		OutputStream os = resp.getOutputStream();
		//获取图片的相对位置，并返回字节输入流
		InputStream is = this.getClass().getResourceAsStream("test.jpg");
		byte[] b =new byte[1024];
		int len =0;
		while((len=is.read())!=-1){
			os.write(b, 0, len);
		}
		os.flush();
		os.close();
	}

}
