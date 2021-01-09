package design.behavioral.command.player.actions;


import design.behavioral.command.player.IAction;
import design.behavioral.command.player.LGPlayer;

/**
 * 停止播放指令StopAction类
 */
public class StopAction implements IAction
{
    private LGPlayer LGPlayer;

    public StopAction(design.behavioral.command.player.LGPlayer LGPlayer) {
        this.LGPlayer = LGPlayer;
    }

    public void execute() {
        LGPlayer.stop();
    }
}
