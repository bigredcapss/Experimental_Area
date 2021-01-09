package design.behavioral.command.player.actions;


import design.behavioral.command.player.IAction;
import design.behavioral.command.player.LGPlayer;

/**
 * 拖动进度条指令SpeedAction类
 */
public class SpeedAction implements IAction
{
    private LGPlayer LGPlayer;

    public SpeedAction(LGPlayer LGPlayer) {
        this.LGPlayer = LGPlayer;
    }

    public void execute() {
        LGPlayer.speed();
    }
}
