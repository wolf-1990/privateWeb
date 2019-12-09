package coreJava.overwrite;

/**
 * @Description TODO 在运行时根据实际类型确定方法执行版本的分派过程称为动态分配。
 * @Author 赵赫智
 * @Date 2019/5/15 15:20
 **/
public class DynamicDispatch {
    static abstract class Human {
        protected abstract void sayHello();
    }

    static class Man extends Human {
        @Override
        protected void sayHello() {
            System.out.println("man say hello");
        }
    }

    static class Woman extends Human {
        @Override
        protected void sayHello() {
            System.out.println("woman say hello");
        }
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        man.sayHello();
        woman.sayHello();
        man = new Woman();
        man.sayHello();
    }

}
