package DesignPattern.proxy.staticProxy;

/**
 * @Description TODO
 * @Author 赵赫智
 * @Date 2019/5/15 11:41
 **/
public class StaticProxy implements StaticHelloWorld{
    //代理类中含有对真实对象的引用
    private StaticHelloWorld staticHelloWorld;

    public StaticProxy(StaticHelloWorld staticHelloWorld) {
        this.staticHelloWorld = staticHelloWorld;
    }

    @Override
    public void print() {
        System.out.println("Before Hello World!");
        staticHelloWorld.print();
        System.out.println("After Hello World!");
    }
}
