package io;

import java.io.*;

/**
 * @Description:使用字节流读取，然后使用转换流转化为字符流
 * InputStreamReader OutputStreamWriter
 * --->
 * 更高效的方式使用缓冲流封装当前的流
 * @Author:BigRedCaps
 */
public class ConvertDemo
{
    public static void main (String[] args)
    {
        try(BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream("D:/we.txt"))){
            // InputStreamReader OutputStreamWriter

            // 字节转换输入流
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            // bufferedReader.readLine()可以读取一行数据
            System.out.println(bufferedReader.readLine());

            /*
            System.out.println(bufferedReader.readLine());
                        等价于
            int len = 0;
            char[] chars = new char[1024];
            while((len = bufferedReader.read(chars))!=-1)
            {
                System.out.println(new String(chars,0,len));
            }
            */

            // 字节转换输出流
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream("D:/we_cp.txt"),"UTF-8");
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write("你好，蓝鸽");
            bufferedWriter.flush();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
