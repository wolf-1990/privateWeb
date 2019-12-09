package DesignPattern.Decorator;

/**
 * @Description TODO
 * @Author 赵赫智
 * @Date 2019/5/14 16:18
 **/
public class DrinkDecorator implements DrinkComponent{
    DrinkComponent component;

    public DrinkDecorator(DrinkComponent component) {
        this.component = component;
    }

    @Override
    public void operation() {
        component.operation();
    }
}
