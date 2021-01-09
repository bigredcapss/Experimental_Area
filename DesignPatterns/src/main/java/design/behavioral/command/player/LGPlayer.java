package design.behavioral.command.player;

/**
 * 播放器内核LGPlayer类
 */
public class LGPlayer
{
    public void play(){
        System.out.println("正常播放");
    }

    public void speed(){
        System.out.println("拖动进度条");
    }

    public void stop(){
        System.out.println("停止播放");
    }

    public void pause(){
        System.out.println("暂停播放");
    }
}
