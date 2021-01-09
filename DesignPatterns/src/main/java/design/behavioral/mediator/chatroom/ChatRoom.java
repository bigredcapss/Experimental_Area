package design.behavioral.mediator.chatroom;

/**
 * 聊天室类
 */
public class ChatRoom {

    public void showMsg(User user,String msg){
        System.out.println("[" + user.getName() + "] : " + msg);
    }
}
