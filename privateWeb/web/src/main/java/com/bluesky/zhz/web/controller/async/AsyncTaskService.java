package com.bluesky.zhz.web.controller.async;

import org.springframework.scheduling.annotation.Async;

/**
 * @Description TODO
 * @Author 赵赫智
 * @Date 2019/5/9 11:35
 **/
//@Service
public class AsyncTaskService {
    @Async
    public void executeAsyncTask(Integer n){
        System.out.println("异步任务执行："+n);
    }
    @Async
    public void executeAsyncTaskPlus(Integer n){
        System.out.println("异步任务执行+1："+n);
    }
}
