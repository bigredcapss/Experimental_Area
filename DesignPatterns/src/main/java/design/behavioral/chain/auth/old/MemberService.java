package design.behavioral.chain.auth.old;


import design.behavioral.chain.auth.Member;
import org.apache.commons.lang.StringUtils;

/**
 * 没用责任链模式之前的写法
 * 主要逻辑是：做登录前的数据验证
 *
 * 注意逻辑的先后顺序：
 * 首先做非空判断，然后检查账号是否有效，然后获得用户角色，然后再根据用户角色所拥有的权限再匹配是否有操作权限
 */
public class MemberService {

    public void login(String loginName,String loginPass){
        if(StringUtils.isEmpty(loginName) ||
                StringUtils.isEmpty(loginPass)){
            System.out.println("用户名和密码为空");
            return;
        }
        System.out.println("用户名和密码不为空，可以往下执行");

        Member member = checkExists(loginName,loginPass);
        if(null == member){
            System.out.println("用户不存在");
            return;
        }
        System.out.println("登录成功！");

        if(!"管理员".equals(member.getRoleName())){
            System.out.println("您不是管理员，没有操作权限");
            return;
        }
        System.out.println("允许操作");

    }

    private Member checkExists(String loginName,String loginPass){
        Member member = new Member(loginName,loginPass);
        member.setRoleName("管理员");
        return member;
    }

    public static void main(String[] args) {
        MemberService service = new MemberService();
        service.login("tom","666");
    }

}
