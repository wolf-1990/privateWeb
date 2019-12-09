package DesignPattern.Decorator;

/**
 * @Description TODO
 * @Author 赵赫智
 * @Date 2019/5/14 16:17
 **/
public class SugarDecorator extends DrinkDecorator{
    public SugarDecorator(DrinkComponent component) {
        super(component);
    }
    public void operation()
    {
        component.operation();
        System.out.print(",with Sugar");
    }
}
