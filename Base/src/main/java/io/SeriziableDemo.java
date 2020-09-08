package io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @Description:序列化与反序列化
 * @Author:BigRedCaps
 */
public class SeriziableDemo
{
    public static void main (String[] args)
    {
        User user = new User("We",18);
        // 将user对象反序列化到磁盘
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("D:/user"))){
            objectOutputStream.writeObject(user);
        }catch(Exception e){
            e.printStackTrace();
        }

        // 将磁盘上的user对象序列化到内存
        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("D:/user"))){
            Object object = objectInputStream.readObject();
            System.out.println(object);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
