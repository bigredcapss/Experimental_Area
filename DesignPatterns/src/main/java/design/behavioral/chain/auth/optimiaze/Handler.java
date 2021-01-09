package design.behavioral.chain.auth.optimiaze;

import design.behavioral.chain.auth.Member;

/**
 * 抽象处理者角色
 */
public abstract class Handler {
    protected Handler next;
    public void next(Handler next){ this.next = next;}

    public abstract void doHandler(Member member);
}
