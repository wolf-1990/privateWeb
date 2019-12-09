package coreJava.jdk8;

/**
 * @Description TODO
 * @Author 赵赫智
 * @Date 2019/5/16 16:06
 **/
public interface InterfaceWithDefaultMethods {
    void performAction();
    default void performDefaulAction(){
        System.out.println("aa");
    }
    static void createAction(){
        System.out.print("bb");
    }
}
