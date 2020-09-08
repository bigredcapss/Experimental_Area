package io.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Description:NIO的写操作
 * @Author:BigRedCaps
 */
public class NIOWriteDemo
{
    public static void main (String[] args)
    {
        try(FileOutputStream fos = new FileOutputStream("D:/test.txt")){

            FileChannel fileChannel = fos.getChannel();

            // 分配缓冲区大小
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            /*这两种写法
                byte[] bytes = new byte[1024];
                ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
            与ByteBuffer byteBuffer = ByteBuffer.allocate(1024);等价
            */
            // 向缓冲区写数据
            byteBuffer.put("Hello,I am a NIO program".getBytes());
            // 为输出数据做好准备
            byteBuffer.flip();
            /*
             * 这里有两层含义：
             * 一是读取byteBuffer中的数据
             * 二是将byteBuffer中的数据写入fileChannel
             *
             * 也就是说对于byteBuffer是读，对于fileChannel是写
             * */
            fileChannel.write(byteBuffer);

            // 将当前缓冲区byteBuffer中的数据清空
            byteBuffer.clear();

        }catch (Exception e){

        }
    }
}
