package design.behavioral.chain.auth.builderchain;

import design.behavioral.chain.auth.Member;

/**
 * 基于optimiaze包的代码用建造者模式进行改造
 */
public class MemberService {

    public void login(String loginName,String loginPass){

        Handler.Builder builder = new Handler.Builder();

        builder.addHandler(new ValidateHandler());
//                .addHandler(new LoginHandler())
//                .addHandler(new AuthHandler());

        builder.build().doHandler(new Member(loginName,loginPass));


        //用过Netty的人，肯定见过
    }

}
