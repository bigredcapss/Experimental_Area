package io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:ServerSocket的API使用--ServerSocket进程
 * @Author:BigRedCaps
 */
public class ServerSocketClassDemo
{
    // 启动一个服务端
    public static void main (String[] args)
    {
        final int DEFAULT_PORT = 8080;
        ServerSocket serverSocket = null;
        try
        {
            // 向OS注册一个服务，绑定监听的端口
            serverSocket = new ServerSocket(DEFAULT_PORT);

            // 构建一个线程池
            ExecutorService executorService = Executors.newFixedThreadPool(5);

            while(true)
            {
                // 阻塞操作(如果没有任何客户端去发起连接到当前客户端的话，这里处于阻塞状态)，等待客户端的连接
                // 这里的Socket相当于一个管道，之后的IO操作都要基于这个管道
                Socket socket = serverSocket.accept();

                // 当接收一个请求之后，不需要等待，即使在run()里面睡眠，它只会阻塞原来的IO操作，并不会对
                // 服务端的连接进行阻塞，所以可以接收多个连接去独立处理，又因为线程可以并行执行，所以它可以
                // 提升系统的性能，从而达到处理多个连接的目的
                ServerSocketThread serverSocketThread = new ServerSocketThread(socket);
                // 提交给线程池
                executorService.submit(serverSocketThread);
            }

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
