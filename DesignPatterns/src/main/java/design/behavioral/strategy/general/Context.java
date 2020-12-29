package design.behavioral.strategy.general;

/**
 * 上下文角色--用来操作策略的上下文环境，屏蔽高层模块对策略，算法的直接访问，封装可能存在的变化
 * @author BigRedCaps
 * @date 2020/12/28 18:18
 */
public class Context
{
    private IStrategy iStrategy;

    public Context(IStrategy iStrategy){
        this.iStrategy = iStrategy;
    }

    public void algorithm(){
        this.iStrategy.algorithm();
    }

}
