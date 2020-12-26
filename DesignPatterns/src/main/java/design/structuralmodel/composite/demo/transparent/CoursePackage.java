package design.structuralmodel.composite.demo.transparent;

import java.util.ArrayList;
import java.util.List;

public class CoursePackage extends CourseComponent {
    private List<CourseComponent> items = new ArrayList<CourseComponent>();
    private String name;
    private Integer level;


    public CoursePackage(String name, Integer level) {
        this.name = name;
        this.level = level;
    }

    @Override
    public void addChild(CourseComponent catalogComponent) {
        items.add(catalogComponent);
    }

    @Override
    public String getName(CourseComponent catalogComponent) {
        return this.name;
    }

    @Override
    public void removeChild(CourseComponent catalogComponent) {
        items.remove(catalogComponent);
    }

    @Override
    public void print() {
        System.out.println(this.name);

        for(CourseComponent catalogComponent : items){
            //控制显示格式
            if(this.level != null){
                for(int  i = 0; i < this.level; i ++){
                    //打印空格控制格式
                    System.out.print("  ");
                }
                for(int  i = 0; i < this.level; i ++){
                    //每一行开始打印一个+号
                    if(i == 0){ System.out.print("+"); }
                    System.out.print("-");
                }
            }
            //打印标题
            catalogComponent.print();
        }
    }

}
