package design.structuralmodel.proxy.staticproxy;

/**
 * 静态代理测试
 *
 * 静态代理：显式声明被代理对象（随着需求的扩大，静态代理不易扩展）；
 *
 * 随着经济的发展，社会竞争压力的增大，越来越多的人开始没时间找对象，婚介所应运而生
 *
 * 于是乎，就有了动态代理模式
 *
 */
public class Test {
    public static void main(String[] args) {
        ZhangLaosan zhangLaosan = new ZhangLaosan(new ZhangSan());
        zhangLaosan.findLove();
    }
}
