package io;

import java.io.*;

/**
 * @Description:通过缓冲流实现Copy功能，与普通的IO流的性能比较
 * @Author:BigRedCaps
 */
public class BufferedCopyDemo
{
    private static final File fileSource = new File("C:\\Users\\mi\\Pictures\\qingxin0809.rar");
    private static final File fileTarget = new File("C:\\Users\\mi\\Pictures\\qingxin0809_cp.rar");;

    /**
     * 普通IO流实现复制功能
     * 当然普通IO流也可以通过调整bytes数组的大小，来减少磁盘IO的次数，提高效率；
     */
    public void copyWithNormal()
    {
        try(
            FileInputStream fileInputStream = new FileInputStream(fileSource);
            FileOutputStream fileOutputStream = new FileOutputStream(fileTarget)
            ){
            byte[] bytes = new byte[1024];
            int len = 0;
            while((len = fileInputStream.read(bytes))!=-1)
            {
                fileOutputStream.write(bytes,0,len);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 缓冲流实现复制功能
     */
    public void copyWithBuffered()
    {
        try(
            FileInputStream fileInputStream = new FileInputStream(fileSource);
            FileOutputStream fileOutputStream = new FileOutputStream(fileTarget);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)
            ){
            byte[] bytes = new byte[1024];
            int len = 0;
            while((len = bufferedInputStream.read(bytes))!=-1)
            {
                bufferedOutputStream.write(bytes,0,len);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main (String[] args)
    {
        BufferedCopyDemo bufferedCopyDemo = new BufferedCopyDemo();
        long start = System.currentTimeMillis();
        bufferedCopyDemo.copyWithNormal();
        System.out.println("普通IO流复制耗时："+(System.currentTimeMillis()-start));
        start = System.currentTimeMillis();
        bufferedCopyDemo.copyWithBuffered();
        System.out.println("缓冲IO流复制耗时："+(System.currentTimeMillis()-start));
    }
}
