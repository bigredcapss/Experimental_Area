package single;

/**
 * @Description:学生
 * @Author:BigRedCaps
 */
public class Student
{
    private Integer id;//编号
    private String name;//姓名
    private Double sal;//薪水
    public Student(){}
    public Student(Integer id, String name, Double sal) {
        this.id = id;
        this.name = name;
        this.sal = sal;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Double getSal() {
        return sal;
    }
    public void setSal(Double sal) {
        this.sal = sal;
    }
}
