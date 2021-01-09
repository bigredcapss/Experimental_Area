package design.behavioral.observer.gper;

/**
 * 模仿技术社区提问问题场景：
 * 当你在社区提问时，如果有设置指定老师回答，对应的老师就会收到邮件通知，这就时观察者模式的一个应用场景
 */
public class Test {
    public static void main(String[] args) {
        GPer gper = GPer.getInstance();
        Teacher tom = new Teacher("Tom");
        Teacher jerry = new Teacher("Jerry");

        gper.addObserver(tom);
        gper.addObserver(jerry);

        //用户行为
        Question question = new Question();
        question.setUserName("张三");
        question.setContent("观察者模式适用于哪些场景？");

        gper.publishQuestion(question);
    }
}
