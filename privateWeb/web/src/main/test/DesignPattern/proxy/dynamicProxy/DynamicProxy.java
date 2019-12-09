package DesignPattern.proxy.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Description TODO
 * 该代理类的内部属性是Object类型，实际使用的时候通过该类的构造方法传递进来一个对象。
 * 该类实现了invoke()方法，该方法中的method.invoke()其实就是调用被代理对象的将要执行的方法，
 * 通过动态代理类，我们可以在执行真实对象的方法前后加入自己的一些额外方法
 * @Author 赵赫智
 * @Date 2019/5/15 11:58
 **/
public class DynamicProxy implements InvocationHandler {
    //对真实对象的引用
    private Object target;

    public DynamicProxy(Object target) {
        this.target = target;
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
