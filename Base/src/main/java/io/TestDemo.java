package io;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * @Description:IOs数据来源
 * @Author:BigRedCaps
 */
public class TestDemo
{
    public static void main (String[] args) throws IOException
    {
        // 磁盘IO
        /*try
        {
            FileInputStream fileInputStream = new FileInputStream("D:/test.txt");
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }*/

        // 内存IO
        /*String str = "hello world";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes());
        int read = 0;
        while((read = byteArrayInputStream.read())!=-1)
        {
            System.out.print((char)read);
        }*/

        // 键盘IO
        // Scanner
        // 读取控制台输入
        InputStream inputStream = System.in;
        int read = 0;
        while((read = inputStream.read())!=-1)
        {
            System.out.print((char)read);
        }

        // 网络IO
        Socket socket = null;
        socket.getInputStream();
        socket.getOutputStream();
    }
}
