package DesignPattern.proxy.dynamicProxy;

/**
 * @Description TODO
 * @Author 赵赫智
 * @Date 2019/5/15 12:04
 **/
public class DynamicTestMain1 {
    public static void main(String[] args){
        DynamicProxy1 dp = new DynamicProxy1();
        DynamicHelloWorld dhwi = new DynamicHelloWorldImpl();
        DynamicHelloWorld dhw = (DynamicHelloWorld)dp.newInstance(dhwi);
        dhw.print();
    }
}
