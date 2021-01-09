package design.behavioral.state.general.apply;

// 抽象状态：State
public abstract class State {
    protected Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    public abstract void handle();
}