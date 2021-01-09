package design.structuralmodel.facade.general;

/**
 * 子系统角色A
 *
 * 注：可以有一个或多个SubSystem。每个SubSystem都不是一个单独的类，而是一个类的集合。SubSystem并不知道Facade的存在
 * 对于SubSystem而言，Facade只是另一个客户端而已。
 * @author BigRedCaps
 * @date 2020/12/30 20:34
 */
public class SubSystemA
{
    public void doA(){
        System.out.println("doing A stuff");
    }
}
