package design.behavioral.command.player;

import java.util.ArrayList;
import java.util.List;

/**
 * 控制条类
 * 注：控制条可以执行单条命令，也可以批量执行多条命令
 */
public class Controller {
    private List<IAction> actions = new ArrayList<IAction>();

    public void addAction(IAction action){
        actions.add(action);
    }

    public void execute(IAction action){
        action.execute();
    }

    public void executes(){
        for (IAction action:actions) {
            action.execute();
        }
        actions.clear();
    }



}
