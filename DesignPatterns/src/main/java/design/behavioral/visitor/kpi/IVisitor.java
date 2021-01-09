package design.behavioral.visitor.kpi;

/**
 * 访问者类型的定义
 */
public interface IVisitor {

    // 访问工程师类型
    void visit (Engineer engineer);

    // 访问经理类型
    void visit (Manager manager);

}
