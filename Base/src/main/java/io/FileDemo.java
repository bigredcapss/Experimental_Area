package io;

import java.io.*;

/**
 * @Description:根据用户端输入的路径进行目录的遍历
 * @Author:BigRedCaps
 */
public class FileDemo
{
    public static void main (String[] args) throws IOException
    {
        // 获取控制台的字符输入流
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        // 对字符输入流封装一层缓冲流
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        try
        {
            // 获取控制台输入的文件路径
            String path = bufferedReader.readLine();
            File file = new File(path);
            // 这里还可以对File进行封装,将file转换为文件输入流
            // FileInputStream fileInputStream = new FileInputStream(file);
            // 判断file类型
            if(file.isDirectory()&&file.exists()){
                // 遍历该目录以及该目录的所有子目录
                fileList(file);
            }else{
                System.out.println("路径输入错误");
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            bufferedReader.close();
            inputStreamReader.close();
        }

    }

    private static void fileList(File filePath)
    {
        File[] files = filePath.listFiles();
        for (int i = 0; i < files.length; i++)
        {
            if(files[i].isFile()){
                System.out.println(files[i].getName());
            }else{
                fileList(files[i]);// 递归遍历
            }

        }
    }
}
