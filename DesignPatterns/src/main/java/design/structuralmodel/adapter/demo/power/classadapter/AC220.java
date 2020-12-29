package design.structuralmodel.adapter.demo.power.classadapter;


public class AC220 {
    public int outputAC220V(){
        int output = 220;
        System.out.println("输出电压" + output + "V");
        return output;
    }
}
