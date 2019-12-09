package DesignPattern.command;

/**
 * @Description TODO
 * @Author 赵赫智
 * @Date 2019/5/15 14:15
 **/
public abstract class Command {
    //命令接收者：电视机
    protected Television television;

    public Command(Television television) {
        this.television = television;
    }

    //命令执行
    abstract void execute();
}
