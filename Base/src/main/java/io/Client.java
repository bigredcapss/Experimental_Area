package io;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @Description:客户端进程
 * @Author:BigRedCaps
 */
public class Client
{
    public static void main (String[] args) throws IOException
    {
        Socket socket = new Socket("localhost", 8080);
        // 发送数据到服务端
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("Hello World".getBytes("UTF-8"));
        // 关闭连接
        outputStream.close();
        socket.close();

    }
}
