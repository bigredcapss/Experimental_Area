package design.behavioral.visitor.kpi;

/**
 * CEO访问者
 */
public class CEOVistitor implements IVisitor {
    public void visit(Engineer engineer) {
        System.out.println("工程师" +  engineer.name + "，KIP：" + engineer.kpi);
    }

    public void visit(Manager manager) {
        System.out.println("经理：" +  manager.name + ",KPI:" + manager.kpi + "，产品数量：" + manager.getProducts());
    }
}
