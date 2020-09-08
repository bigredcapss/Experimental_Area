package io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * @Description:基于内存的输入输出流Demo
 * @Author:BigRedCaps
 */
public class MemoryDemo
{
    static String str = "Hello World";
    public static void main (String[] args)
    {
        // 从内存中读取数据
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes());
        // 向内存中写入数据
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        while((i = byteArrayInputStream.read())!=-1)
        {
            char c = (char)i;
            byteArrayOutputStream.write(Character.toUpperCase(c));
        }
        System.out.println(byteArrayOutputStream.toString());

        /**
         * 这里没必要调用close方法关闭流，因为没有使用本地资源，即使你调用close()方法，也没有什么效果
         */
    }

}
