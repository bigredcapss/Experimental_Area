package io;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

/**
 * @Description:Flush()方法Demo
 * @Author:BigRedCaps
 */
public class FlushDemo
{
    /*public static void main (String[] args) throws IOException
    {
        BufferedOutputStream bufferedOutputStream =
                new BufferedOutputStream(new FileOutputStream("D:/we.txt"));
        // 会发现we.txt里面并没有Hello world字符串
        bufferedOutputStream.write("Hello world".getBytes());
    }*/

    public static void main (String[] args)
    {
        /**
         * 这里用try(){}catch(Exception e){}语句块，jdk帮我们触发了close()方法（或者说是帮我们关闭了流），
         * 而close()方法也会触发刷盘动作；
         */
        try(BufferedOutputStream bufferedOutputStream =
                new BufferedOutputStream(new FileOutputStream("D:/we.txt"))){
            bufferedOutputStream.write("Hello world".getBytes());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
