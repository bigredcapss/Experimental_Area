package design.behavioral.chain.auth.optimiaze;

/**
 * 利用责任链模式进行数据校验拦截测试
 */
public class Test {
    public static void main(String[] args) {
        MemberService memberService = new MemberService();
        memberService.login("we","666");
    }
}
