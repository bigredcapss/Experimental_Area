package design.creationmode.flyweight.jdk.integer;

/**
 * Integer中的享元模式测试
 * 注意：Integer.valueOf()源码
 */
public class IntegerTest {
    public static void main(String[] args) {

        Integer a = Integer.valueOf(100);
        Integer b = 100;

        Integer c = Integer.valueOf(1000);
        Integer d = 1000;

        System.out.println("a==b:" + (a==b));
        System.out.println("c==d:" + (c==d));
    }
}
