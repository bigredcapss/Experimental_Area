package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Description:实现图片复制功能
 * @Author:BigRedCaps
 */
public class CopyPictureDemo
{
    // I input  从磁盘读取到内存
    // O output 从内存写入到磁盘
    public static void main (String[] args) throws IOException
    {
        File file = new File("D:/java.png");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileOutputStream fileOutputStream = new FileOutputStream("D:/java_cp.png");

        int len = 0;
        // 创建缓冲
        byte[] buffer = new byte[1024];
        /**
         * len = fileInputStream.read(buffer))!=-1 和 fileOutputStream.write(len);编号①
         *                                  VS
         * len = fileInputStream.read())!=-1 和 fileOutputStream.write(buffer,0,len);编号②
         *
         * 编号②是每读取一次，写入一个字节，需要频繁的进行IO写入；假如有1000个字节，就需要写1000次磁盘；
         * 编号①是先把数据读取到缓存里面，再把缓存写入到磁盘中；加入图片的大小是1024个字节，那么只需要写一次就可以了；
         * 如果大于1024个字节，每写满1024个字节，就和磁盘交互一次；
         */
        while((len = fileInputStream.read())!=-1)
        {
            //fileOutputStream.write(buffer,0,len);
            // 读取的数据既可以保存到内存中，也可以写出到磁盘中
            fileOutputStream.write(len);// 将InputStream的输入字节写出到指定目录下
        }
        // 如果不关闭的话，IO操作的该文件会一直处于占用状态，使得其它程序无法进行任何操作；
        System.in.read();// 阻塞主线程，导致D:/java.png和D:/java_cp.png文件无法被操作
        fileOutputStream.close();
        fileInputStream.close();
        /**
         * 为什么JVM不帮我们去关闭呢？
         * 因为对于外部资源（例如数据库连接，redis连接，文件IO等）的对接，JVM是无法控制的；JVM只能控制JVM内部的一些资源；
         *
         * 但是为了解决这样一个问题呢？
         *
         * 在JDK1.7中，帮我们做了这样一件事，如果不想手动关闭，可以这样做：
         * try(
         *     // 自动帮我们关闭流-->前提：对应的IO类要实现Closeable接口，否则是不会被关闭的；
         *     FileInputStream fileInputStream = new FileInputStream(file);
         *     FileOutputStream fileOutputStream = new FileOutputStream("D:/java_cp.png");
         *     ){
         *     //对流的操作...
         * }cathc (Exception e){
         * }
         */
    }
}
