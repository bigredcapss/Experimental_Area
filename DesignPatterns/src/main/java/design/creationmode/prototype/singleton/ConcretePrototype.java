package design.creationmode.prototype.singleton;


import lombok.Data;

import java.util.List;

/**
 * 克隆破坏单例模式事故
 *
 * 原型模式与单例模式就是相对立的；
 * 单例模式：单例对象是通过构造函数创建的
 * 原型模式：对象是基于内存中的二进制流复制来的；
 *
 * 如果我们克隆的目标的对象是单例对象，那意味着深克隆就会破坏单例，那如何防止克隆破坏单例？
 * 方法1.禁止深克隆；
 * 方法2：重写clone()方法，在clone方法中返回单例对象；
 *
 *
 * 可以参考ArrayList,HashMap的源码的克隆方法更好的理解这一事故
 * 看看ArrayList是如何实现深克隆的
 */
@Data
public class ConcretePrototype implements Cloneable {

    private int age;
    private String name;
    private List<String> hobbies;

    private static  ConcretePrototype instance = new ConcretePrototype();

    private ConcretePrototype(){}

    public static ConcretePrototype getInstance(){
        return instance;
    }

    @Override
    public ConcretePrototype clone() {
       return instance;
    }


    @Override
    public String toString() {
        return "ConcretePrototype{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", hobbies=" + hobbies +
                '}';
    }
}
