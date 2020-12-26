package design.structuralmodel.composite.demo.safe;

import java.util.ArrayList;
import java.util.List;

public class Folder extends Directory {
    private List<Directory> dirs;

    private Integer level;

    public Folder(String name,Integer level) {
        super(name);
        this.level = level;
        this.dirs = new ArrayList<Directory>();
    }

    @Override
    public void show() {
        System.out.println(this.name);
        for (Directory dir : this.dirs) {
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
            //打印名称
            dir.show();
        }
    }

    public boolean add(Directory dir) {
        return this.dirs.add(dir);
    }

    public boolean remove(Directory dir) {
        return this.dirs.remove(dir);
    }

    public Directory get(int index) {
        return this.dirs.get(index);
    }

    public void list(){
        for (Directory dir : this.dirs) {
            System.out.println(dir.name);
        }
    }

}