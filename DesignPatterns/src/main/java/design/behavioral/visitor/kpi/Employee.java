package design.behavioral.visitor.kpi;

import java.util.Random;

/**
 * 员工基类
 */
public abstract class Employee {
    public String name;
    public int kpi;  //员工KPI

    public Employee(String name) {
        this.name = name;
        kpi = new Random().nextInt(10);
    }

    //接收访问者的访问
    public abstract void accept(IVisitor visitor);
}
