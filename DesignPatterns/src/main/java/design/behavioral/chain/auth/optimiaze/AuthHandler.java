package design.behavioral.chain.auth.optimiaze;

import design.behavioral.chain.auth.Member;

/**
 * 权限校验
 */
public class AuthHandler extends Handler {

    @Override
    public void doHandler(Member member) {
        if(!"管理员".equals(member.getRoleName())){
            System.out.println("您不是管理员，没有操作权限");
            return;
        }
        System.out.println("允许操作");
    }
}
