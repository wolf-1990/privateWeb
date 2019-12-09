package DesignPattern.proxy.staticProxy;

/**
 * @Description TODO 真实角色
 * @Author 赵赫智
 * @Date 2019/5/15 11:40
 **/
public class StaticHelloWorldImpl implements StaticHelloWorld {
    @Override
    public void print() {
        System.out.println("Hello World!");
    }
}
