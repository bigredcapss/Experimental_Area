package design.behavioral.command.player;


import design.behavioral.command.player.actions.PauseAction;
import design.behavioral.command.player.actions.PlayAction;
import design.behavioral.command.player.actions.SpeedAction;
import design.behavioral.command.player.actions.StopAction;

/**
 * 播放器Demo测试---播放器有播放，拖动进度条，停止播放，暂停播放功能
 *
 * 我们自己去操作播放器的时候并不是直接调用播放器的方法，而是通过一个控制条去传达指令给播放器内核，那么具体传达什么指令，
 * 会被封装为一个一个的按钮。那么每个按钮就相当于是对一条命令的封装。用控制条实现了用户发送指令与播放器内核接收指令的解耦
 *
 *
 * 由于控制条已经与播放器内核解耦了，以后如果想扩展新命令，只需要增加命令即可，控制条的结构无需改动；
 */
public class Test {
    public static void main(String[] args) {

        LGPlayer player = new LGPlayer();
        Controller controller = new Controller();
        controller.execute(new PlayAction(player));

        controller.addAction(new PauseAction(player));
        controller.addAction(new PlayAction(player));
        controller.addAction(new StopAction(player));
        controller.addAction(new SpeedAction(player));
        controller.executes();
    }
}
