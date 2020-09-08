package io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @Description:
 * @Author:BigRedCaps
 */
public class SocketChannelDemo
{
    public static void main (String[] args)
    {
        try
        {
            // 创建SocketChannel
            SocketChannel socketChannel = SocketChannel.open();

            // 把客户端设置为非阻塞
            socketChannel.configureBlocking(false);

            // 请求连接指定主机和端口的服务
            // 在非阻塞模式下，这段代码并不一定是要等到连接建立之后才执行
            socketChannel.connect(new InetSocketAddress("localhost",8080));

            // 非阻塞模式下
            if(socketChannel.isConnectionPending()){
                // 完成连接建立
                socketChannel.finishConnect();
            }

            // 向缓冲区写数据
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            byteBuffer.put("Hello,I am SocketChannel Client01".getBytes());
            byteBuffer.flip();
            // 把缓冲区数据写出去
            socketChannel.write(byteBuffer);

            // 读取服务端返回的数据
            byteBuffer.clear();
            /**
             * 这里会阻塞，因为服务端睡眠10s后，才会返回数据-->
             * 客户端改为非阻塞后，这里服务端没有返回时，这里已经变为了非阻塞，
             * 也就是说，服务端返回前，socketChanel.read(byteBuffer)返回的数据是空
             */
            int r = socketChannel.read(byteBuffer);
            if(r>0)
            {
                System.out.println("收到服务端的消息："+new String(byteBuffer.array()));
            }else{
                System.out.println("服务端的数据还未返回");
            }

            // 避免客户端写出后就执行结束，导致连接中断，从而引起服务端异常；
            // 因为当服务端读取一个中断的连接时，会出现异常；这里加上System.in.read不让线程结束
            //System.in.read();

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
