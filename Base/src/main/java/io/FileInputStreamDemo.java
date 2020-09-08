package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @Description:
 * @Author:BigRedCaps
 */
public class FileInputStreamDemo
{
    public static void main (String[] args)
    {
        FileInputStream fileInputStream = null;

        try
        {
            fileInputStream = new FileInputStream("D:/test.txt");
            // 表示读取到的数据对应的ASCII码
            int read = 0;
            // 使用read方法每次读取一个字节，返回对应的int值(ASCII码)
            while((read = fileInputStream.read())!=-1)
            {
                System.out.print((char)read);
            }
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
