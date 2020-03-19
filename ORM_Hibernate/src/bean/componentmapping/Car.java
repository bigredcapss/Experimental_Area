package bean.componentmapping;

/**
 * @Description:汽车实体
 * @Author:BigRedCaps
 */
public class Car
{
    private int id;
    private String name;
    // 车轮
    private Wheel wheel;
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
    public Wheel getWheel() {
        return wheel;
    }
    public void setWheel(Wheel wheel) {
        this.wheel = wheel;
    }
}
