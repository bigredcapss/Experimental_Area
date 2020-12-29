package design.structuralmodel.adapter.demo.power.classadapter;

public class Test {
    public static void main(String[] args) {
        DC5 adapter = new PowerAdapter();
        adapter.output5V();
    }
}
