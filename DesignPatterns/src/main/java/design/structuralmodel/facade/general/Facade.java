package design.structuralmodel.facade.general;

/**
 * 门面角色/外观角色--系统对外的统一接口
 * @author BigRedCaps
 * @date 2020/12/30 20:32
 */
public class Facade
{
    private SubSystemA a = new SubSystemA();
    private SubSystemB b = new SubSystemB();
    private SubSystemC c = new SubSystemC();

    // 对外接口A
    public void doA(){
        this.a.doA();
    }
    // 对外接口B
    public void doB(){
        this.b.doB();
    }
    // 对外接口C
    public void doC(){
        this.c.doC();
    }

}
