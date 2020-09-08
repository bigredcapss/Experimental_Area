package io;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Description:服务端进程
 * @Author:BigRedCaps
 */
public class Server
{
    public static void main (String[] args)
    {
        ServerSocket serverSocket = null;
        try
        {
            serverSocket = new ServerSocket(8080);
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();

            byte[] bytes = new byte[1024];
            int len = 0;
            while((len = inputStream.read(bytes))!=-1)
            {
                System.out.println(new String(bytes,0,len,"UTF-8"));
            }
            // 关闭连接
            inputStream.close();
            socket.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
