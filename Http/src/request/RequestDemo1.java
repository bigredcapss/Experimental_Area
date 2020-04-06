package request;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

/**
 * @Description:
 * @Author:BigRedCaps
 */
public class RequestDemo1 extends HttpServlet
{
    /**
     * 1)tomcat服务器接收到浏览器发送的请求数据，然后封装到HttpServetRequest对象
     * 2)tomcat服务器调用doGet方法，然后把request对象传入到servlet中。
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /**
         * 3)从request对象取出请求数据。
         */
        t1(request);

        t2(request);


    }

    // 为了接收POST方式提交的请求
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp)
            throws ServletException, IOException
    {
        /**
         * 3.3 请求的实体内容
         */
        InputStream in = request.getInputStream(); //得到实体内容
        byte[] buf = new byte[1024];
        int len = 0;
        while(  (len=in.read(buf))!=-1 ){
            String str = new String(buf,0,len);
            System.out.println(str);
        }
    }

    private void t2(HttpServletRequest request) {
        /**
         * 3.2 请求头
         */
        String host = request.getHeader("Host"); //根据头名称的到头的内容
        System.out.println(host);

        //遍历所有请求头
        Enumeration<String> enums = request.getHeaderNames(); //得到所有的请求头名称列表
        while(enums.hasMoreElements()){//判断是否有下一个元素
            String headerName = enums.nextElement(); //取出下一个元素
            String headerValue = request.getHeader(headerName);
            System.out.println(headerName+":"+headerValue);
        }
    }

    private void t1(HttpServletRequest request) {
        /**
         * 3.1 请求行   格式：（GET /day09/hello HTTP/1.1）
         */
        System.out.println("请求方式："+request.getMethod());//请求方式
        System.out.println("URI:"+request.getRequestURI());//请求资源
        System.out.println("URL:"+request.getRequestURL());
        System.out.println("http协议版本："+request.getProtocol());//http协议
    }
}
