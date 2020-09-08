package io;

import java.io.Serializable;

/**
 * @Description:实现序列化接口的User对象
 * @Author:BigRedCaps
 */
public class User implements Serializable
{
    private String name;
    private Integer age;

    public User (String name, Integer age)
    {
        this.name = name;
        this.age = age;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public Integer getAge ()
    {
        return age;
    }

    public void setAge (Integer age)
    {
        this.age = age;
    }

    @Override
    public String toString ()
    {
        return "User{" + "name='" + name + '\'' + ", age=" + age + '}';
    }
}
