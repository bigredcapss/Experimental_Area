package thread;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author:BigRedCaps
 */
public class StaticDemo
{
    private StaticDemo(){}
    private static StaticDemo instance = new StaticDemo();
    public static StaticDemo getInstance(){
        return instance;
    }
}

class FinalDemo{
    private final Map<String,String> states;
    public FinalDemo(){
        states = new HashMap<>();
        states.put("name","we");
    }
}

class VolatileSyncDemo{
    private VolatileSyncDemo(){ }
    private static VolatileSyncDemo instance = null;

    /**
     * DCL问题：
     * instance = new VolatileSyncDemo();---->
     * 1.memory = allocate()：该指令分配内存空间
     * 2.actrInstance(memory)：该指令真正初始化
     * 3.instance = memory：该指令赋值
     *
     * 若指令执行顺序变为了1，3，2则意味着实例还未进行初始化时就把它赋值给了instance实例，因此造成实例的不完整性
     */
    public static VolatileSyncDemo getInstance(){
        if(instance==null){
            synchronized (VolatileSyncDemo.class){
                if(instance==null){
                    instance = new VolatileSyncDemo();
                }
            }

        }
        return instance;
    }
}
