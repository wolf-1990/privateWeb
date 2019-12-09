package DesignPattern.proxy.dynamicProxy;

/**
 * @Description TODO 真实角色
 * @Author 赵赫智
 * @Date 2019/5/15 11:56
 **/
public class DynamicHelloWorldImpl implements DynamicHelloWorld {
    @Override
    public String print() {
        System.out.println("Enter DynamicHelloWorldImpl.print()");
        return "DynamicHelloWorldImpl";
    }
}
