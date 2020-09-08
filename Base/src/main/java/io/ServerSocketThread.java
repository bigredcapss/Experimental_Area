package io;

import java.io.*;
import java.net.Socket;

/**
 * @Description: 服务端代码交给线程池去执行
 * @Author:BigRedCaps
 */
public class ServerSocketThread implements Runnable
{
    Socket socket;

    public ServerSocketThread (Socket socket)
    {
        this.socket = socket;
    }

    @Override
    public void run ()
    {
        // 代码执行到这里，说明已经有客户端连接成功了
        System.out.println("客户端："+socket.getPort()+"已连接");
        try
        {
            // 然后构建IO进行客户端的输入和基于服务端的回写
            // 因为我们基于的是TCP协议，TCP协议是一个双工协议，所谓双工协议就是可以双方相互通信的协议；
            // 构建一个高效的字符缓冲输入流，【这里当客户端没有传过来数据时，这里仍让时阻塞的】
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // 获取客户端输入的信息
            String clientStr = null;
            clientStr = bufferedReader.readLine();
            System.out.println("收到客户端的请求信息："+clientStr);
            // 基于服务端的回写的字符缓冲输出流
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            // 这里加\n换行，否则客户端的readLine()会一直处于阻塞状态,会报出Connection reset异常信息
            bufferedWriter.write("我已经收到了消息\n");
            bufferedWriter.flush();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
