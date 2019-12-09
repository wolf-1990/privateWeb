package DesignPattern.Decorator;

/**
 * @Description TODO
 * @Author 赵赫智
 * @Date 2019/5/14 16:21
 **/
public class TeaDecorator extends DrinkDecorator{

    public TeaDecorator(DrinkComponent component) {
        super(component);
    }

    public void operation()
    {
        component.operation();
        System.out.print(",with Tea");
    }
}
