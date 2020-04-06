package request;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description:获取浏览器类型
 * @Author:BigRedCaps
 */
public class RequestDemo3 extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=utf-8");
        //获取请求头： user-agent
        String userAgent = request.getHeader("user-agent");
        System.out.println(userAgent);

        //判断用户使用的浏览器类型
        if(userAgent.contains("Firefox")){
            response.getWriter().write("你正在使用火狐浏览器");
        }else if(userAgent.contains("Chrome")){
            response.getWriter().write("你正在使用谷歌浏览器");
        }else if(userAgent.contains("Trident")){
            response.getWriter().write("你正在使用IE浏览器");
        }else{
            response.getWriter().write("地球上没有这个浏览器，建议使用火狐浏览器");
        }
    }

}
