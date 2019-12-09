package com.bluesky.zhz.web.controller.async;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description TODO
 * @Author 赵赫智
 * @Date 2019/5/9 11:39
 **/
//@Component
//@Slf4j
public class DealThreadTask {
    private ExecutorService fixedThreadPool;
    @PostConstruct
    public void init(){
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(6);
        this.fixedThreadPool = fixedThreadPool;
    }
    public void execute(String name){
        for (int j = 1; j < 3; j++) {
            List<TestLcc> testLccList = new ArrayList<>();
            for (int i = 1; i < 3; i++) {
                testLccList.add(new TestLcc(i, name + i, (int) (Math.random() * 100)));
            }
            MoniotrTask moniotrTask = new MoniotrTask(testLccList, name + "线程" + j);
            if (!fixedThreadPool.isShutdown()) {
                fixedThreadPool.execute(moniotrTask);
            }
        }
    }

}
