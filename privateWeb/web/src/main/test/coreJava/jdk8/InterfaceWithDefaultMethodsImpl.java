package coreJava.jdk8;


import org.junit.Test;

/**
 * @Description TODO
 * @Author 赵赫智
 * @Date 2019/5/16 16:08
 **/
public class InterfaceWithDefaultMethodsImpl implements InterfaceWithDefaultMethods {
    @Override
    public void performAction() {
        this.performDefaulAction();
        InterfaceWithDefaultMethods.createAction();
    }
    @Test
    public void aa(){
        performAction();
    }
}
