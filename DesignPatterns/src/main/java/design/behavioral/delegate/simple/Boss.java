package design.behavioral.delegate.simple;

/**
 * 老板给经理下任务--委派任务
 * @author BigRedCaps
 * @date 2020/12/27 11:17
 */
public class Boss
{
    public void command(String task,Leader leader){
        leader.doing(task);
    }

}
