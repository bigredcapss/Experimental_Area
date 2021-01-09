package design.behavioral.command.player.actions;


import design.behavioral.command.player.LGPlayer;
import design.behavioral.command.player.IAction;

/**
 * 暂停指令类
 */
public class PauseAction implements IAction
{
    private LGPlayer gplayer;

    public PauseAction(LGPlayer gplayer) {
        this.gplayer = gplayer;
    }

    public void execute() {
        gplayer.pause();
    }
}
