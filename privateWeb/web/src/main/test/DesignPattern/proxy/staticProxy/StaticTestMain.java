package DesignPattern.proxy.staticProxy;

/**
 * @Description TODO
 * @Author 赵赫智
 * @Date 2019/5/15 11:42
 **/
public class StaticTestMain {
    public static void main(String[] args){
        StaticHelloWorld shw=new StaticHelloWorldImpl();
        StaticProxy sp=new StaticProxy(shw);
        sp.print();
    }
}
