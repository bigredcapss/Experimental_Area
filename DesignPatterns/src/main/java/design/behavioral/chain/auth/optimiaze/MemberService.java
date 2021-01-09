package design.behavioral.chain.auth.optimiaze;


import design.behavioral.chain.auth.Member;

/**
 * 串联校验逻辑
 * 缺点：当链式结构较长时，MemberService的工作会非常繁琐，并且MemberService的代码会非常臃肿，且后续更改处理者或者消息类型
 * 时，都必须再MemberService中进行修改，不符合开闭原则。产生这些问题的原因是因为链式结构的组装过于复杂。因此后续可以用
 * 建造者模式改进代码
 */
public class MemberService {

    public void login(String loginName,String loginPass){
        Handler validateHandler = new ValidateHandler();
        Handler loginHandler = new LoginHandler();
        Handler authHandler = new AuthHandler();

        validateHandler.next(loginHandler);
        loginHandler.next(authHandler);

        validateHandler.doHandler(new Member(loginName,loginPass));

    }

}
