package design.creationmode.builder.chain;

import org.apache.ibatis.mapping.CacheBuilder;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;

/**
 * 建造者模式链式写法--较常用
 *
 *
 * 建造者模式在源码中的应用：可参考JDK的StringBuilder，Mybatis的CacheBuiler,SqlSessionFactoryBuilder
 * Spring的BeanDefinitionBuilder
 *
 * 建造者模式可以让你在构建对象的时候更加灵活，实现对象构建的定制；
 *
 * 建造者模式就是把过程开放给你，具体的结果的构建细节，你不要关心
 *
 */
public class Test {
    public static void main(String[] args) {
        CourseBuilder builder = new CourseBuilder()
                    .addName("设计模式")
                    .addPPT("【PPT课件】")
                    .addVideo("【回放视频】")
                    .addNote("【课堂笔记】")
                    .addHomework("【课后作业】");

        System.out.println(builder.build());



        StringBuilder sb = new StringBuilder();
        sb.append("");


        CacheBuilder cacheBuilder = new CacheBuilder("");
        cacheBuilder.blocking(false);
//        cacheBuilder.

        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
//        sqlSessionFactoryBuilder.


        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition();
//        beanDefinitionBuilder.
















    }
}
