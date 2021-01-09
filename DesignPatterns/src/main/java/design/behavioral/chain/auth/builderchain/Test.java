package design.behavioral.chain.auth.builderchain;


public class Test {
    public static void main(String[] args) {
        MemberService memberService = new MemberService();
        memberService.login("jane","666");
    }
}
