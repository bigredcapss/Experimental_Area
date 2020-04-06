package bean.extendsmapping;

/**
 * @Description:抽象基类--动物类--继承映射
 * @Author:BigRedCaps
 */
public abstract class Animal
{
    private int id;
    private String name;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
