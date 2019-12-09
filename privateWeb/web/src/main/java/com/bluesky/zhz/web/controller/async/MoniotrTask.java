package com.bluesky.zhz.web.controller.async;

import java.sql.Connection;
import java.util.List;

/**
 * @Description TODO
 * @Author 赵赫智
 * @Date 2019/5/9 11:47
 **/
public class MoniotrTask implements Runnable{
    private List<TestLcc> lccList;
    private String name;
    private Connection connection;

    public MoniotrTask(List<TestLcc> lccList, String name) {
        this.lccList = lccList;
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("启动" + name + "线程");
        for (int i = 0; i < lccList.size(); i++) {
            System.out.println(lccList.get(i).toString());
        }
    }
}
