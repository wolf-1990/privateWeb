package DesignPattern.Decorator;

/**
 * @Description TODO
 * @Author 赵赫智
 * @Date 2019/5/14 16:16
 **/
public class WaterComponent implements DrinkComponent{
    @Override
    public void operation() {
        System.out.print("water drink");
    }
}
