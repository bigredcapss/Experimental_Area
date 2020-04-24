package design.creationmode.prototype.shallow;

import java.util.ArrayList;
import java.util.List;

/**
 * 浅克隆测试
 * 浅克隆：仅仅只是复制了值类型的数据，并没有复制引用对象；
 */
public class Client {


    public static void main(String[] args) {
        //创建原型对象
        ConcretePrototype prototype = new ConcretePrototype();
        prototype.setAge(18);
        prototype.setName("Tom");
        List<String> hobbies = new ArrayList<String>();
        hobbies.add("书法");
        hobbies.add("美术");
        prototype.setHobbies(hobbies);

        //拷贝原型对象
        ConcretePrototype cloneType = prototype.clone();
        cloneType.getHobbies().add("技术控");//这里仅仅对克隆对象进行了修改


        System.out.println("原型对象：" + prototype);
        System.out.println("克隆对象：" + cloneType);
        //原型对象和克隆对象的地址是否一样呢？
        System.out.println(prototype == cloneType);


        System.out.println("原型对象的爱好：" + prototype.getHobbies());
        System.out.println("克隆对象的爱好：" + cloneType.getHobbies());
        System.out.println(prototype.getHobbies() == cloneType.getHobbies());

        //得出结论：原型对象和克隆对象的地址不一样，但是它们共用了一个hobbies的内存地址

    }

}
