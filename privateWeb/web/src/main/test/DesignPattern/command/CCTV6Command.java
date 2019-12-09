package DesignPattern.command;

/**
 * @Description TODO
 * @Author 赵赫智
 * @Date 2019/5/15 14:20
 **/
public class CCTV6Command extends Command {
    public CCTV6Command(Television television) {
        super(television);
    }

    @Override
    void execute() {
        television.playCctv6();
    }
}
