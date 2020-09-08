package thread;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:通过破坏占有且等待条件来规避死锁
 * 通过第三方角色，来对锁的申请和释放做一个统一的调配，
 * 从而一次性的申请所有的资源，达到破坏占有且等待条件的目的；
 * @Author:BigRedCaps
 */
public class Allocater
{
    private List<Object> list = new ArrayList<>();

    /**
     * 申请资源的方法
     * @param from
     * @param to
     * @return
     */
    synchronized boolean apply(Object from,Object to){
        if(list.contains(from)||list.contains(to))
        {
            return false;
        }else{
            list.add(from);
            list.add(to);
            return true;
        }
    }

    /**
     * 释放资源的方法
     * @param from
     * @param to
     */
    synchronized void free(Object from,Object to){
        list.remove(from);
        list.remove(to);
    }
}
