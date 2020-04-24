package design.creationmode.prototype.shallow;


import lombok.Data;

import java.util.List;

/**
 * 通过JDK官方提供的API实现浅克隆
 *
 * 浅克隆：该克隆方法，基于内存二进制流进行克隆/复制，之后，赋值给对应的克隆对象，对于引用类型而言，仅仅是把引用类型指向
 * 的内存地址给复制了一份，赋值给了克隆对象；而对于值类型的数据，则进行了完整的拷贝；
 *
 * 实现Cloneable接口的都是浅克隆
 */
@Data
public class ConcretePrototype implements Cloneable {

    //基本类型
    private int age;
    private String name;
    //引用类型
    private List<String> hobbies;

    @Override
    public ConcretePrototype clone() {
        try {
            return (ConcretePrototype)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
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
