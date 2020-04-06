package response;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description:请求重定向
 * @Author:BigRedCaps
 */
public class ResponseDemo2 extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        /**
         * 需求： 跳转到adv.html
         * 使用请求重定向： 发送一个302状态码+location的响应头
         */
		/*response.setStatus(302);//发送一个302状态码
		response.setHeader("location", "/day09/adv.html"); //location的响应头
*/

        //请求重定向简化写法
        response.sendRedirect("/html/adv.html");


    }

}
