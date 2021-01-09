package design.behavioral.visitor.dispatch.staticdispatch;

/**
 * 静态分配测试
 *
 * 在学习Java的多态时，我们知道引用变量的类型分为编译时类型和运行时类型。而这里根据对象的类型而对方法进行的选择，就是分派
 * （Dispatch）。分派又分为两种，静态分配和动态分配。
 *
 * 静态分配---按照变量的类型的静态类型进行分派，从而确定方法的执行版本，静态分派在编译时期就可以确定方法的版本，而静态分派
 * 最典型的应用场景就是方法重载；在静态分派时，我们根据多个判断依据（即方法的参数类型和方法个数）判断出了方法的版本，这个
 * 就是多分派的概念
 *
 * 动态分派---对于动态分派，与静态分派相反，它不是在编译期确定的方法版本，而是在运行是才能确定，而动态分派最典型的应用场景
 * 就是多态的特性。
 */
public class Main {
    public void test(String string){
        System.out.println("string" + string);
    }
    public void test(Integer integer){
        System.out.println("integer" + integer);
    }

    public static void main(String[] args) {
        String string = "1";
        Integer integer = 1;
        Main main = new Main();
        main.test(integer);
        main.test(string);
    }
}
