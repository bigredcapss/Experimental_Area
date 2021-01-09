package design.behavioral.mediator.chatroom;

/**
 * 使用中介者模式设计群聊场景测试
 *
 * 假设要构建一个聊天室系统，用户可以向聊天室发送消息，聊天室会向所有用户展示消息。实际上就是用户发信息与聊天室显示的通信过程
 * 不过用户无法直接将信息发给聊天室，而是需要将信息先发送到服务器上，然后服务器在将该消息发给聊天室进行显示。
 */
public class Test {
    public static void main(String[] args) {
        ChatRoom chatRoom = new ChatRoom();

        User tom = new User("Tom",chatRoom);
        User jerry = new User("Jerry",chatRoom);

        tom.sendMessage("Hi! I am Tom.");
        jerry.sendMessage("Hello! My name is Jerry.");
    }
}
