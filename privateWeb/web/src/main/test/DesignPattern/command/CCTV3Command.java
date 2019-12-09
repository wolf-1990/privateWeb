package DesignPattern.command;

/**
 * @Description TODO
 * @Author 赵赫智
 * @Date 2019/5/15 14:18
 **/
public class CCTV3Command extends Command{
    public CCTV3Command(Television television) {
        super(television);
    }
    @Override
    void execute() {
        television.playCctv3();
    }
}
