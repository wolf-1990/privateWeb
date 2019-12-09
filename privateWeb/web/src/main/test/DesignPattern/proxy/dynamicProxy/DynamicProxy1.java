package DesignPattern.proxy.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description TODO
 * @Author 赵赫智
 * @Date 2019/5/15 12:03
 **/
public class DynamicProxy1 implements InvocationHandler {
    private Object target;

    public Object newInstance(Object target){
        this.target = target;
        //创建代理类实例
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //目标方法之前执行
        System.out.println("Before DynamicProxy");
        //通过反射机制来调用目标类方法
        method.invoke(target, args);
        //目标方法之后执行
        System.out.println("After DynamicProxy");
        return null;
    }
}
