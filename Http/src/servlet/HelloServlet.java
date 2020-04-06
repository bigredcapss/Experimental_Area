package servlet;

import javax.servlet.http.HttpServlet;
import java.io.IOException;

/**
 * @Description:
 * @Author:BigRedCaps
 */
public class HelloServlet extends HttpServlet
{
    protected void doPost (javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException
    {

    }

    protected void doGet (javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException
    {
        //向浏览器输出内容
        response.getWriter().write("this is hello servlet!!!");
    }
}
