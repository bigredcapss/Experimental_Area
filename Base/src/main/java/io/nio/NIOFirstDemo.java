package io.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Description:使用NIO实现文件复制功能
 * @Author:BigRedCaps
 */
public class NIOFirstDemo
{
    public static void main (String[] args)
    {
        try
        {
            FileInputStream fis = new FileInputStream(new File("D:/test.txt"));
            FileOutputStream fos = new FileOutputStream(new File("D:/test_cp.txt"));

            FileChannel fin = fis.getChannel();
            FileChannel fout = fos.getChannel();

            // 初始化缓冲区
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            // 读取数据到缓冲区
            fin.read(byteBuffer);

            byteBuffer.flip();

            // 将缓冲区的数据写出
            fout.write(byteBuffer);

            byteBuffer.clear();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
