package design.behavioral.command.player.actions;


import design.behavioral.command.player.LGPlayer;
import design.behavioral.command.player.IAction;

/**
 * 播放指令PlayAction类----创建操作播放器可以接收的指令
 */
public class PlayAction implements IAction
{
    private LGPlayer gplayer;

    public PlayAction(LGPlayer gplayer) {
        this.gplayer = gplayer;
    }

    public void execute() {
        gplayer.play();
    }
}
