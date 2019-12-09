package DesignPattern.proxy.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @Description TODO
 * @Author 赵赫智
 * @Date 2019/5/15 12:00
 **/
public class DynamicTestMain {
    public static void main(String[] args){
        DynamicHelloWorld dhwi=new DynamicHelloWorldImpl();
        InvocationHandler ih=new DynamicProxy(dhwi);
        //创建代理实例(使用Proxy类和自定义的调用处理逻辑(handler)来生成一个代理对象)
        DynamicHelloWorld dhw=(DynamicHelloWorld) Proxy.
                newProxyInstance(DynamicHelloWorld.class.getClassLoader(), new Class<?>[]{DynamicHelloWorld.class}, ih);
        //调用方法时，转移给handler接管，由其中的invoke()方法实际完成方法执行
        dhw.print();
    }
}
