package design.behavioral.state.gper;

/**
 * 抽象状态角色---UserState类
 */
public abstract class UserState {
    protected AppContext context;

    public void setContext(AppContext context) {
        this.context = context;
    }

    public abstract void favorite();

    public abstract void comment(String comment);
}
