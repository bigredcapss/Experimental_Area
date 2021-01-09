package design.behavioral.state.gper;

/**
 * 状态模式测试类
 *
 * 我们在社区阅读文章时，如果觉得文章写得好，我们就会评论，收藏两联发。如果处于登录情况下，我们就可以直接做评论，收藏这些
 * 行为。否则，跳转到登录页面，登录后再继续执行先前的动作，这里涉及的状态有两种：登录与未登录；行为有两种：评论，收藏；
 */
public class Test {
    public static void main(String[] args) {
        AppContext context = new AppContext();
        context.favorite();
        context.comment("评论：好文章，360个赞");
    }
}
