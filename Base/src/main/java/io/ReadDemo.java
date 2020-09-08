package io;

import java.io.FileInputStream;

/**
 * @Description:
 * @Author:BigRedCaps
 */
public class ReadDemo
{
    public static void main (String[] args)
    {
        try(FileInputStream fileInputStream = new FileInputStream("D:/test.txt")){
            // buffer的大小并不是越大越好，需要设置一个合理的值
            byte[] buffer = new byte[3]; // 设置大小为3,需要执行4次写入IO操作（test.txt的长度为11）
            //byte[] buffer = new byte[11];
            //byte[] buffer = new byte[1024*1024]; // 占用内存空间，有可能导致内存溢出

            // 当byte数组大小为3时，执行4次IO操作： [][][]->[H][e][l]->[l][o][，]->[W][o][r]->[l][d][r]
            // 为了解决该问题，设置为new String(buffer,0,i)即输出指定长度的字节

            int i = 0;
            while((i = fileInputStream.read(buffer))!=-1)
            {
                // 通过使用print可以明显看到执行多少次IO操作
                System.out.println(new String(buffer,0,i));
            }
        }catch (Exception e){

        }
    }
}
