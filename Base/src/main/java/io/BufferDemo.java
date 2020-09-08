package io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @Description:缓冲输入流与缓冲输出流
 * @Author:BigRedCaps
 */
public class BufferDemo
{
    public static void main (String[] args)
    {
        // 将文件输入流放入缓冲输入流,文件输出流放入缓冲输出流
        try(
            // 默认都是8Kb的大小
            BufferedInputStream bufferedInputStream =
                        new BufferedInputStream(new FileInputStream("E:/test.txt"));
            BufferedOutputStream bufferedOutputStream =
                        new BufferedOutputStream(new FileOutputStream("E/test_cp.txt"))
         ) {
            byte[] buffer = new byte[1024];
            int len = 0;
            while((len=bufferedInputStream.read(buffer))!=-1)
            {
                System.out.println(new String(buffer,0,len));
                bufferedOutputStream.write(buffer,0,len);
                // 刷新缓冲区
                bufferedOutputStream.flush();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
