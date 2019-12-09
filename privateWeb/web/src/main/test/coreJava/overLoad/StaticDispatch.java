package coreJava.overLoad;

/**
 * @Description TODO 虚拟机在重载时是通过参数的静态类型而不是实际类型作为判定依据的，
 * TODO 静态类型又是在编译器可知的，所以在编译时就能确定使用哪个重载版本。
 * TODO 所有依赖静态类型来定位方法执行版本的分派动作称为静态分派。
 * @Author 赵赫智
 * @Date 2019/5/15 15:10
 **/
public class StaticDispatch {
    static abstract class Human{
    }

    static class Man extends Human {
    }

    static class Woman extends Human {
    }

    public void sayHello(Human guy) {
        System.out.println("hello, guy!");
    }

    public void sayHello(Man guy) {
        System.out.println("hello, gentleman!");
    }

    public void sayHello(Woman guy) {
        System.out.println("hello, lady!");
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        StaticDispatch staticDispatch = new StaticDispatch();
        staticDispatch.sayHello(man);
        staticDispatch.sayHello(woman);
    }

}
