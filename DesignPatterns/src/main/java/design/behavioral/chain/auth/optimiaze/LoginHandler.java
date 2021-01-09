package design.behavioral.chain.auth.optimiaze;

import design.behavioral.chain.auth.Member;

/**
 * 登录校验
 */
public class LoginHandler extends Handler {
    public void doHandler(Member member) {
        System.out.println("登录成功！");
        member.setRoleName("管理员");
        next.doHandler(member);
    }
}
