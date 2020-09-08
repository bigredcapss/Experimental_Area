package io;

import java.io.FileInputStream;

/**
 * @Description:字节流读取中文字符时出现乱码的一种讨巧的解决方法
 * @Author:BigRedCaps
 */
public class ByteReadDemo
{
    public static void main (String[] args)
    {
        try(FileInputStream fis = new FileInputStream("D:/we.txt")){
            //讨巧的方法
            byte[] bytes = new byte[1024];
            int i = 0;
            while((i=fis.read(bytes))!=-1)
            {
                System.out.print(new String(bytes,0,i));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
