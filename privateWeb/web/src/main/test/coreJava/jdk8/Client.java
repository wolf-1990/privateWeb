package coreJava.jdk8;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @Description TODO
 * @Author 赵赫智
 * @Date 2019/5/16 16:07
 **/
public class Client {
    public void runMe(final Runnable r){
        r.run();
    }
    @Test
    public void main() {
        runMe(()->System.out.print("run!"));
        Collections.unmodifiableCollection(new ArrayList<>());
        new Thread(()->{System.out.print("aa");}).start();

    }
}
