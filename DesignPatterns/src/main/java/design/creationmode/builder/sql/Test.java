package design.creationmode.builder.sql;

import java.util.Arrays;

/**
 * spring开源框架JPA的sql构造模式
 */
public class Test {
    public static void main(String[] args) {
        QueryRule queryRule = QueryRule.getInstance();
//        queryRule.addAscOrder("age");
//        queryRule.andEqual("addr","Changsha");
//        queryRule.andLike("name","Tom");

        /**
         * 这里无论你怎么拼，都会符合sql的语法，这个事情是用建造者模式帮你做到的
         *
         * 不分谁先谁后
         */

        //链式写法
        queryRule.addAscOrder("age")
        .andEqual("addr","Changsha")
        .andLike("name","Tom");


        //根据查询规则，构造QueryRuleSqlBuilder对象
        QueryRuleSqlBuilder builder = new QueryRuleSqlBuilder(queryRule);

        //传一个表名，自动帮我们构造sql语句
        System.out.println(builder.builder("t_member"));

        System.out.println("Params: " + Arrays.toString(builder.getValues()));


    }
}
