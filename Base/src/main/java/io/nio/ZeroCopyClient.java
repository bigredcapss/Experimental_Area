package io.nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * @Description:
 * @Author:BigRedCaps
 */
public class ZeroCopyClient
{
    public static void main (String[] args) throws IOException
    {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost",8080));
        // 48M的数据
        FileChannel fileChannel = new FileInputStream("D:/GPlayer_windows_v1.0.9.zip").getChannel();

        // 传输数据到socketChannel上,返回tf总的字节数【零拷贝的关键代码】
        /**
         * 不需要再把数据从本地磁盘拷贝到内核缓冲区，再拷贝到用户空间，再拷贝到内核缓冲区，....
         * 直接通过transferTo进行传输,传输到socketChannel中
         */
        // 这里需要内核缓冲区循环不断的读这个数据，直到读取完
        int position = 0;
        long size = fileChannel.size();
        while(size>0)
        {
            long tf = fileChannel.transferTo(position, size, socketChannel);
            if(tf>0)
            {
                position+=tf;
                size-=tf;
            }
        }
        fileChannel.close();
        socketChannel.close();
    }
}
