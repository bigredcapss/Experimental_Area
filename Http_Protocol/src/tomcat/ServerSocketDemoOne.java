package tomcat;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Description:Socket服务端程序：启动该socket程序，在浏览器端输入http://localhost:8888 即可访问该文件
 * @Author:BigRedCaps
 */
public class ServerSocketDemoOne
{
    public static void main (String[] args) throws IOException
    {
        //创建ServerSocket
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("服务器已经启动");
        while(true)
        {
            //接收客户端响应
            Socket socket = serverSocket.accept();
            //读取本地的html文件
            FileInputStream fileInputStream = new FileInputStream(new File("d:\\1-selecter.html"));
            //构建数据输出通道
            OutputStream outputStream = socket.getOutputStream();
            //发送数据
            byte[] buf = new byte[1024];
            int len = 0;
            while((len=fileInputStream.read(buf))!=-1)
            {
                outputStream.write(buf,0,len);
            }
            //关闭资源
            outputStream.close();
            fileInputStream.close();

        }

    }
}
