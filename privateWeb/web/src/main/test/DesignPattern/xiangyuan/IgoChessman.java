package DesignPattern.xiangyuan;

/**
 * @Description TODO
 * @Author 赵赫智
 * @Date 2019/5/15 10:42
 **/
//围棋棋子类：抽象享元类
public abstract  class IgoChessman {
    public abstract String getColor();

    public void display(Coordinates coord) {
        System.out.println("棋子颜色：" + this.getColor() + "，棋子位置："
                + coord.getX() + "，" + coord.getY() );
    }
}
