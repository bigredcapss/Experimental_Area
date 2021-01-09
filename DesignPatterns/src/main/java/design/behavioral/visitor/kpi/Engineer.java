package design.behavioral.visitor.kpi;

import java.util.Random;

/**
 * 工程师
 */
public class Engineer extends Employee {
    public Engineer(String name) {
        super(name);
    }

    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

    //考核指标是每年的代码量
    public int getCodeLines(){
        return new Random().nextInt(10* 10000);
    }
}
