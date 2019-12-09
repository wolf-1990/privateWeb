package DesignPattern.Decorator;

import org.junit.Test;

/**
 * @Description TODO
 * @Author 赵赫智
 * @Date 2019/5/14 16:22
 **/
public class TestDecorator {
    @Test
    public void test(){
        WaterComponent water=new WaterComponent();
        SugarDecorator sugar=new SugarDecorator(water);
        TeaDecorator tea=new TeaDecorator(sugar);
        tea.operation();
    }
}
