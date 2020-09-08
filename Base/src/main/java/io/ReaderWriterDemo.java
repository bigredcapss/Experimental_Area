package io;

import java.io.FileReader;
import java.io.FileWriter;

/**
 * @Description:文件复制功能Demo
 * @Author:BigRedCaps
 */
public class ReaderWriterDemo
{
    public static void main (String[] args)
    {
        try(FileReader fileReader = new FileReader("D:/we.txt");
            FileWriter fileWriter = new FileWriter("D:/we_cp.txt")
        ){

            // 字符流使用char[](字符数组)
            char[] chars = new char[1024];
            int len = 0;

            while((len = fileReader.read(chars))!=-1)
            {
                fileWriter.write(chars,0,len);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
