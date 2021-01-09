package design.behavioral.visitor.kpi;

/**
 * 利用访问者模式实现KPI考核的场景：
 * 每到年底，管理层就要开始评定员工一年的工作绩效，员工分为工程师和经理；管理层有CEO和CTO。那么CEO关注的是工程师的KPI和
 * 经理的KPI以及新产品的数量；由于CEO和CTO对于不同的员工的关注点是不一样的，这就需要对不同的员工类型进行不同的处理；
 *
 * 工程师考核的是代码数量；经理考核的是产品数量，二者的职责不一样。也正是因为有这样的差异性，才能使得访问者模式能够在这个
 * 场景下发挥作用。Employee,Engineer,Manager这3个类型就相当于数据结构，这些类型相对稳定，不会发生变化；然后将这些员工添加
 * 到一个业务报表类中，公司高层可以通过该报表类的showReport()方法查看所有员工的业绩。
 */
public class Test {
    public static void main(String[] args) {
        BusinessReport report = new BusinessReport();
        System.out.println("==========CEO看报表===============");
        report.showReport(new CEOVistitor());
        System.out.println("==========CTO看报表===============");
        report.showReport(new CTOVistitor());
    }
}
