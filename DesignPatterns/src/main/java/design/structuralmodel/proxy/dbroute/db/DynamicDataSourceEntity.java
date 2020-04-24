package design.structuralmodel.proxy.dbroute.db;

/**
 * 动态切换数据源所用到的数据源模拟实体
 */
public class DynamicDataSourceEntity {

    public final static String DEFAULE_SOURCE = null;

    private final static ThreadLocal<String> local = new ThreadLocal<String>();

    private DynamicDataSourceEntity(){}


    public static String get(){return  local.get();}

    public static void restore(){
         local.set(DEFAULE_SOURCE);
    }

    //DB_2018
    //DB_2019
    public static void set(String source){local.set(source);}

    public static void set(int year){local.set("DB_" + year);}

}
