package test;

import bean.manytomany.Developer;
import bean.manytomany.Project;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

/**
 * @Description:项目与开发人员的多对多映射测试
 * @Author:BigRedCaps
 */
public class ProjectDeveloperManyToManyTest
{
    private static SessionFactory sf;
    static
    {
        sf = new Configuration()
                .configure()
                .addClass(Project.class)
                .addClass(Developer.class)
                .buildSessionFactory();
    }

    /**
     * 多对多保存操作测试【只能通过一方维护另外一方，不能重复维护】
     * 设置inverse属性，对多对多保存操作的影响：
     * inverse=false ，有控制权，可以维护关联关系； 保存数据的时候会把对象关系插入桥表；
     * inverse=true,  没有控制权， 不会向桥表插入数据。
     */
    @Test
    public void saveTest()
    {
        Session session = sf.openSession();
        session.beginTransaction();

        /**
         * 模拟数据：
         * 			电商系统（曹吉，王春）
         * 			OA系统（王春，老张）
         */

        // 项目对象
        Project prj_ds = new Project();
        prj_ds.setPrj_name("电商系统");
        Project prj_oa = new Project();
        prj_oa.setPrj_name("OA系统");

        // 员工对象
        Developer dev_cj = new Developer();
        dev_cj.setD_name("曹吉");
        Developer dev_wc = new Developer();
        dev_wc.setD_name("王春");
        Developer dev_lz = new Developer();
        dev_lz.setD_name("老张");

        // 关系维护 【项目方】
        prj_ds.getDevelopers().add(dev_cj);
        prj_ds.getDevelopers().add(dev_wc); // 电商系统（曹吉，王春）
        prj_oa.getDevelopers().add(dev_wc);
        prj_oa.getDevelopers().add(dev_lz); // OA系统（王春，老张）

        //关系维护【开发者方】
        dev_cj.getProjects().add(prj_ds);
        dev_wc.getProjects().add(prj_ds);// 曹吉，王春（电商系统）
        dev_wc.getProjects().add(prj_oa);
        dev_lz.getProjects().add(prj_oa);// 王春，老张（OA系统）

        // 保存操作
		session.save(dev_cj);
		session.save(dev_wc);
		session.save(dev_lz);// 必须要设置级联保存

        //保存操作
        session.save(prj_ds);
        session.save(prj_oa);// 必须要设置级联保存

        /**
         *  // 关系维护 【项目方】
         *         prj_ds.getDevelopers().add(dev_cj);
         *         prj_ds.getDevelopers().add(dev_wc); // 电商系统（曹吉，王春）
         *         prj_oa.getDevelopers().add(dev_wc);
         *         prj_oa.getDevelopers().add(dev_lz); // OA系统（王春，老张）
         *
         * 关系维护【项目方】   与   关系维护【开发者方】只存其一
         *
         *  //关系维护【开发者方】
         *         dev_cj.getProjects().add(prj_ds);
         *         dev_wc.getProjects().add(prj_ds);// 曹吉，王春（电商系统）
         *         dev_wc.getProjects().add(prj_oa);
         *         dev_lz.getProjects().add(prj_oa);// 王春，老张（OA系统）
         *
         *  // 保存操作
         * 		session.save(dev_cj);
         * 		session.save(dev_wc);
         * 		session.save(dev_lz);// 必须要设置级联保存
         *
         * 【两个保存操作同样对应的也只存其一】
         *
         *  //保存操作
         *         session.save(prj_ds);
         *         session.save(prj_oa);// 必须要设置级联保存
         */

        session.getTransaction().commit();
        session.close();
    }


    /**
     * 多对多获取操作测试
     * 设置inverse属性，对多对多获取操作的影响：
     * 无论inverse如何设置，都不影响获取操作；
     */
    @Test
    public void getTest()
    {
        Session session = sf.openSession();
        session.beginTransaction();

        Project prj = (Project) session.get(Project.class, 7);
        System.out.println(prj.getPrj_name());
        System.out.println(prj.getDevelopers());

        session.getTransaction().commit();
        session.close();
    }

    /**
     * 测试inverse属性对解除关系的影响
     * 设置inverse属性， 对解除关系的影响：
     *    inverse=false ,有控制权， 解除关系就是删除中间表的数据；
     *    inverse=true, 没有控制权，不能解除关系，即解除关系会报错；
     */
    @Test
    public void removeRelationTest()
    {

        Session session = sf.openSession();
        session.beginTransaction();

        Project prj = (Project) session.get(Project.class, 7);
        prj.getDevelopers().clear();

        session.getTransaction().commit();
        session.close();
    }


    /**
     * 测试inverse属性对删除数据的影响
     * 设置inverse属性，对删除数据的影响?
     *    inverse=false, 有控制权；先删除中间表数据，再删除自身；
     *    inverse=true, 没有控制权；如果删除的数据有被引用，会报错！ 否则，才可以删除；
     */
    @Test
    public void deleteDataTest()
    {

        Session session = sf.openSession();
        session.beginTransaction();

        Project prj = (Project) session.get(Project.class, 8);
        session.delete(prj);

        session.getTransaction().commit();
        session.close();
    }
}
