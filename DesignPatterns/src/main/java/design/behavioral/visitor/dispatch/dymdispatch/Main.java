package design.behavioral.visitor.dispatch.dymdispatch;

/**
 * 动态分配测试
 */
public class Main {
    public static void main(String[] args) {
        Person man = new Man();
        Person woman = new WoMan();

        man.test();
        woman.test();
    }
}
