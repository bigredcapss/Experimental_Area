package io;

import java.io.*;
import java.net.Socket;

/**
 * @Description:Socket的API使用--客户端进程
 * @Author:BigRedCaps
 */
public class ClientSocketClassDemo
{
    public static void main (String[] args)
    {
        final int DEFAULT_PORT = 8080;
        try
        {
            // 建立一个连接（因为服务端是在本机上，因次host为本机的IP）
            Socket socket = new Socket("localhost",DEFAULT_PORT);

            // 构建高效的字符缓冲输出流，向服务端发送消息
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedWriter.write("我是客户端发送的消息：client-01");
            bufferedWriter.flush();

            // 构建高效的字符缓冲输入流，用于接收服务端发送的消息
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String serverStr = bufferedReader.readLine();
            System.out.println("收到服务端的消息："+serverStr);

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
